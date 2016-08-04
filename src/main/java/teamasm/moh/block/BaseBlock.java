package teamasm.moh.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * Created by brandon3055 on 4/08/2016.
 */
public class BaseBlock extends Block {
    public BaseBlock(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    public BaseBlock(Material materialIn) {
        super(materialIn);
    }
}
