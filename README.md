# API para sistema de condominio
``` mermaid
graph TD
    Residencial --> Resident
    Resident --> Payment
    Resident --> Booking
    Payment --> Transaction
    Transaction --> Wallet
```

### Residencial

- **Descrição**: Representa o condomínio ou complexo residencial.
- **Funcionalidades**: Gerencia os residentes e pagamentos associados.

### Resident

- **Descrição**: Representa um morador do condomínio.
- **Funcionalidades**: Realiza reservas de áreas comuns e realiza pagamentos mensais.
  
### Payment

- **Descrição**: Representa um pagamento mensal associado a um morador.
- **Funcionalidades**: Registra os pagamentos realizados pelos moradores.
  
### Booking

- **Descrição**: Representa uma reserva de área comum feita por um morador.
- **Funcionalidades**: Permite que os moradores reservem áreas comuns do condomínio.
  
### Transaction

- **Descrição**: Representa uma transação financeira.
- **Funcionalidades**: Registra todas as transações financeiras, como pagamentos e recebimentos.
  
### Wallet

- **Descrição**: Representa a carteira financeira do condomínio.
- **Funcionalidades**: Gerencia o saldo e as transações financeiras do condomínio.
  
## Projeto para aprendizagem 
