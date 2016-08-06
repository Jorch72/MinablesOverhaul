package teamasm.moh.api.recipe;

/**
 * Created by covers1624 on 8/6/2016.
 */
public class OreStack {

    public final String mat;
    public final int stackSize;

    public OreStack(String mat, int stackSize) {
        this.mat = mat;
        this.stackSize = stackSize;
    }

    public OreStack(Object object){
        if (object instanceof String){
            this.mat = (String) object;
            this.stackSize = 1;
        } else if (object instanceof OreStack){
            this.mat = ((OreStack) object).mat;
            this.stackSize = ((OreStack) object).stackSize;
        } else{
            throw new IllegalArgumentException("Not a string or OreStack!");
        }
    }


}
