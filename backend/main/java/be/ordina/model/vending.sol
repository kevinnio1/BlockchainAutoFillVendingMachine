pragma solidity ^0.4.8;
contract vendingMachine {

    address owner;
    address client;
    
    int public stock;

    /* this function is executed at initialization and sets the owner of the contract */
    function vendingMachine() { 
        owner = msg.sender;
        stock = 50;
    }

    /* Function to recover the funds on the contract */
    function kill() {
        if (msg.sender == owner) selfdestruct(owner); 
    }
    
    function pay() payable returns (bool) {
        client = msg.sender;
        if(stock>0){
        if(msg.value > 20000000000000000){
            uint256 change = msg.value - 20000000000000000;
            if(!client.send(change)) throw;
        }else if (msg.value < 20000000000000000){
            throw;
        }
        
        stock--;
        }else throw;
        
    }
    
    function stockUp(int amount) payable returns (int) {
        if(amount<=0) throw;
        stock += amount;
        return stock;
        
    }
    
    
   function () {
        throw; // throw reverts state to before call
    }
}