pragma solidity ^0.4.8;
contract vendingMachine {

    address owner;
    address client;
    uint public price_finney;
    address supplier;
    address stakeholder;
    
    int public stock;
    

    /* this function is executed at initialization and sets the owner of the contract */
    function vendingMachine() { 
        owner = msg.sender;
        stock = 50;
        price_finney = 20 finney / 1000000000000000;
        supplier = 0xc7764818C6276AE6E145db9143bbA535c148d6C3;
        stakeholder = 0xDEF240271e9E6b79b06f3a7C4A144D3874e512d2;
    }

    /* Function to recover the funds on the contract */
    function kill() {
        if (msg.sender == owner) selfdestruct(owner); 
    }
    
    function pay() payable returns (bool) {
        client = msg.sender;
        if(stock>0){
        if(msg.value >( price_finney * 1000000000000000)){
            uint256 change = msg.value - (price_finney * 1000000000000000);
            if(!client.send(change)) throw;
        }else if (msg.value < (price_finney * 1000000000000000)){
            throw;
        }
        
        
        if(!stakeholder.send(10 finney)) throw;
        stock--;
        if(stock == 10) stockUp(40);
        return true;
        }else throw;
        
    }
    
    function stockUp(int amount) payable returns (int) {
        if(amount<=0 && stock + amount > 50) throw;
        if(!supplier.send(uint256(amount) * 10 finney)) throw;
        stock += amount;
        
        return stock;
        
    }
    
    
    
    function setPrice(uint newPrice) {
        if(msg.sender == owner){
            price_finney == newPrice * 1000000000000000;
        }
    }
    
    
   function () {
        throw; // throw reverts state to before call
    }
}