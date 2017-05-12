pragma solidity ^0.4.8;

contract VendingMachine {

    address owner;
    uint public finneyPrice;
    address supplier;
    address stakeholder;
    int maxStock;
    int minStock;
    int public stock;
    int public users;
    int public adminUsers;
    address public thisAdress;
    address public sender;
    address[] internal accounts;
    address[] internal admins;



    modifier restrictAccessTo(address[] _collection){
        sender = msg.sender;
        for(uint i = 0; i < _collection.length; i++) {
            if (_collection[i] == msg.sender) {
                _;
                return;
            }
        }

        if(msg.sender == address(this)) {_;return;}


        if(msg.value > 0){
            if(!msg.sender.send(msg.value)) throw;
        }

    }

    modifier onlyOwner(){
        if(owner == msg.sender){
            _;
        }
        if(!msg.sender.send(msg.value)) throw;
    }



    /* this function is executed at initialization and sets the owner of the contract */
    function VendingMachine() {
        owner = msg.sender;
        maxStock = 50;
        minStock = 10;
        stock = maxStock;
        finneyPrice = 20 finney / 1 finney;
        supplier = 0x803E3a0C2Bb93b5555F3313A55F6B0A51d52Ff75;
        stakeholder = 0xdf7463670a2b873263CdE76b2ab235dD0fAF2515;
        add(msg.sender);
        adminUsers++;
        admins.push(msg.sender);
        thisAdress = address(this);
    }

    /* Function to recover the funds on the contract */
    function kill() onlyOwner()  {
        selfdestruct(owner);
    }

    function pay() payable restrictAccessTo(accounts) returns (bool) {

        address client = msg.sender;
        if(stock>0){

            if(msg.value >( finneyPrice * 1 finney)){

                uint256 change = msg.value - (finneyPrice * 1 finney);

                if(!client.send(change)) throw;

            }else if (msg.value < (finneyPrice * 1 finney)){
                if(client.send(msg.value)) throw;
                throw;
            }

            if(stock != maxStock)
            if(!stakeholder.send((((finneyPrice*50)/100) * 1 finney))) throw;

            stock--;

            if(stock == minStock) this.stockUp(maxStock-stock);

            return true;

        }else{
            throw;
        }

    }

    function stockUp(int amount) restrictAccessTo(admins) payable returns (int) {
        if(amount<=0 && stock + amount > maxStock) throw;
        if(!supplier.send(uint256(amount) * 10 finney)) throw;
        stock += amount;

        return stock;

    }

    function setPrice(uint newPrice) onlyOwner() {
        finneyPrice = newPrice;
    }

    function setMaxStock(int newStock) restrictAccessTo(admins) {
        if(stock>newStock || minStock>newStock) throw;
        maxStock= newStock;
    }

    function setMinStock(int newStock) restrictAccessTo(admins) {
        if(maxStock<newStock || stock< newStock) throw;
        minStock= newStock;
    }

    function add(address user){
        for(uint x = 0; x < accounts.length; x++) {
            if (accounts[x] == user) {
                throw;
            }
        }
        accounts.push(user);
        users++;
    }

    function remove(address user) restrictAccessTo(admins){
        for(uint x = 0; x < accounts.length; x++) {
            if (accounts[x] == user) {
                //To fill the gap in the array
                accounts[x] == accounts[accounts.length-1];
                delete accounts[accounts.length-1];
                users--;
                break;
            }
        }
    }

    function removeAdmin(address admin) restrictAccessTo(admins){
        for(uint x = 0; x < admins.length; x++) {
            if (admins[x] == admin) {
                //To fill the gap in the array
                admins[x] == admins[admins.length-1];
                delete admins[admins.length-1];
                adminUsers--;
                break;
            }
        }
    }

    function deleteAccount() restrictAccessTo(accounts){
        for(uint x = 0; x < accounts.length; x++) {
            if (accounts[x] == msg.sender) {
                //To fill the gap in the array
                accounts[x] == accounts[accounts.length-1];
                delete accounts[accounts.length-1];
                users--;
                break;
            }
        }

        for(uint y = 0; y < admins.length; y++) {
            if (admins[y] == msg.sender) {
                //To fill the gap in the array
                admins[y] == admins[admins.length-1];
                delete admins[admins.length-1];
                adminUsers--;
                break;
            }
        }
    }

    function addAdmin(address admin) restrictAccessTo(admins){
        //add admin to the accounts list
        add(admin);
        for(uint x = 0; x < admins.length; x++) {
            if (admins[x] == admin) {
                throw;
            }
        }
        admins.push(admin);
        adminUsers++;
    }


    function () {
        throw; // throw reverts state to before call
    }
}


contract Supplier {

    address owner;
    uint public priceInFinney;

    modifier onlyOwner(){
        if(owner == msg.sender){
            _;
        }
        if(!msg.sender.send(msg.value)) throw;
    }


    /* this function is executed at initialization and sets the owner of the contract */
    function Supplier() {
        owner = msg.sender;
        priceInFinney = 10;
    }

    /* Function to recover the funds on the contract */
    function kill() onlyOwner()  {
        selfdestruct(owner);
    }


    function () {
        throw; // throw reverts state to before call
    }
}