package alloffabric.caveman.world;

import alloffabric.caveman.Caveman;
import com.mojang.datafixers.Dynamic;
import net.minecraft.structure.JungleTempleGenerator;
import net.minecraft.structure.NetherFossilGenerator;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.function.Function;

public class MiniDungeonFeature extends AbstractTempleFeature<DefaultFeatureConfig> {
    public MiniDungeonFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> configFactory) {
        super(configFactory);
    }

    @Override
    protected int getSeedModifier() {
        return 0;
    }

    @Override
    public StructureStartFactory getStructureStartFactory() {
        return Start::new;
    }

    @Override
    public String getName() {
        return Caveman.MODID + ":mini_dungeon";
    }

    @Override
    public int getRadius() {
        return 3;
    }

    public static class Start extends StructureStart {
        public Start(StructureFeature<?> structureFeature, int chunkX, int chunkZ, BlockBox blockBox, int i, long l) {
            super(structureFeature, chunkX, chunkZ, blockBox, i, l);
        }

        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int x, int z, Biome biome) {
            int i = x * 16;
            int j = z * 16;
            NetherFossilGenerator.addPieces(
                structureManager,
                this.children,
                this.random,
                new BlockPos(
                    i + this.random.nextInt(16),
                    10 + this.random.nextInt(230),
                    j + this.random.nextInt(16)
                )
            );
            this.setBoundingBoxFromChildren();
        }
    }
}
