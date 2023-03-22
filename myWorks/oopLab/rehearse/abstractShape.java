package OOP.oopLab.rehearse;

public abstract class abstractShape {
    private String shapeType;

    public abstractShape(String shapeType){
        this.shapeType = shapeType;
    }

    public abstract double area();
    public String getShapeType(){
        return shapeType;
    }
    
}
