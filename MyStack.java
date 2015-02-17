import java.util.Scanner;
public class MyStack<AnyType> {
    
    MyLinkedList<AnyType> linkedList = null;
    
    public MyStack(){
        linkedList = new MyLinkedList<>();
    }
    
    public void push(AnyType a){
        linkedList.add(0, a);
    }
    
    public AnyType pop(){
        if(!linkedList.isEmpty()) return linkedList.remove(0);
        else return null;
    }
    
    public int size(){
        return linkedList.size();
    }
    public static void main(String args[]){
        
        MyStack<String> stack = new MyStack();
        int flag = 0; //for error
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the equation");
        String equation = sc.next();
        String[] eqn = equation.split("(?!^)");
        //System.out.println(eqn);
        for( String a : eqn){
            
            if(a.equals("{") || a.equals("[") || a.equals("(")){
                stack.push(a);
            }else{
                String popVal = stack.pop();
                if (popVal==null){ //Ruling out NullPonter exception
                	flag = 1; 
                	break;
                 }
                if ( !((popVal.equals("(") && a.equals(")"))
                
                        || popVal.equals("[") && a.equals("]") 
                            || popVal.equals("{") && a.equals("}"))){
                    flag = 1; 
                    break;
                }
            }
            
            
        }
        
        if(flag == 1 ){
            System.out.println("Equation is not balanced");
        }
        else{
            if(stack.size() == 0){
                System.out.println("Equation is balanced");
            }
            else{
                System.out.println("Equation is not balanced");
            }
        }     
    }
}
