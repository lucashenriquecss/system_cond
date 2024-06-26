package com.example.system_cond.service;

import com.example.system_cond.dto.TransactionDTO;
import com.example.system_cond.dto.TransactionDTO;
import com.example.system_cond.dto.WalletDTO;
import com.example.system_cond.entity.Residence;
import com.example.system_cond.entity.Transaction;
import com.example.system_cond.entity.Transaction;
import com.example.system_cond.entity.Wallet;
import com.example.system_cond.repository.TransactionRepository;
import com.example.system_cond.repository.WalletRepository;
import com.example.system_cond.utils.InsufficientWalletException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository){
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO){
         //validar
        validate(transactionDTO);
        //criar transac
        Transaction transaction = convertToEntity(transactionDTO);
         var newTransaction = transactionRepository.save(transaction);
        //debitar
        var walletOptional = walletRepository.findById(transactionDTO.getWalletId()).get();

        if("spent".equals(transactionDTO.getType())){
           // validate(transactionDTO);
            walletOptional.setDebit(transactionDTO.getValue());
        }else{
            walletOptional.setAdd(transactionDTO.getValue());
        }

        walletRepository.save(walletOptional);


        return convertToDTO(newTransaction);
    }

    private void validate(TransactionDTO transactionDTO) throws InsufficientWalletException {
        // Verifique se a transação é um gasto
        if ("spent".equals(transactionDTO.getType())) {
            // Busque a carteira pelo ID
            Optional<Wallet> walletOptional = walletRepository.findById(transactionDTO.getWalletId());

            // Verifique se a carteira existe
            if (walletOptional.isPresent()) {
                Wallet wallet = walletOptional.get();

                // Verifique se há saldo suficiente na carteira
                if (wallet.getWallet().compareTo(transactionDTO.getValue()) < 0) {
                    throw new InsufficientWalletException("Saldo insuficiente na carteira.");
                }
            } else {
                throw new IllegalArgumentException("Carteira não encontrada.");
            }
        }
        Optional<Wallet> walletOptional = walletRepository.findById(transactionDTO.getWalletId());
        if(walletOptional.isEmpty()){
            Wallet wallet = new Wallet();
            wallet.setWallet(BigDecimal.valueOf(0));
            walletRepository.save(wallet);
        }
    }

    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setId(transaction.getId());
        transactionDTO.setDescription(transaction.getDescription());
        transactionDTO.setPayer(transaction.getPayer());
        transactionDTO.setPayee(transaction.getPayee());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setTransactionDate(transaction.getCreatedAt());
        transactionDTO.setWalletId(transaction.getWallet().getId());

        return transactionDTO;
    }

    private Transaction convertToEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();

        transaction.setDescription(transactionDTO.getDescription());
        transaction.setValue(transactionDTO.getValue());
        transaction.setType(transactionDTO.getType());
        transaction.setPayee(transactionDTO.getPayee());
        transaction.setPayer(transactionDTO.getPayer());

        Wallet wallet = (Wallet) walletRepository.findById(transactionDTO.getWalletId()).orElse(null);
        transaction.setWallet(wallet);

        return transaction;
    }



}
