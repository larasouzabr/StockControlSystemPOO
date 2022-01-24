package personPackage;

public abstract class Person {
    private String name;
    
    Person (String name){
        this.name = name;
    }

    public String getName(){
     return this.name;
    }
    private void setName(String name){
        this.name = name;
    }
}
