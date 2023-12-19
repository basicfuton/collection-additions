package ca.basicfuton.collectionadditions.creativetab;

import ca.basicfuton.collectionadditions.Main;
import ca.basicfuton.collectionadditions.item.ItemRegistry;
import ca.basicfuton.collectionadditions.block.BlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTabRegistry {
    public static final RegistryObject<Item> ICON_ITEM = ItemRegistry.ICON_ITEM;
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Main.MOD_ID);
    public static final RegistryObject<CreativeModeTab> COLLECTION_TAB = CREATIVE_MODE_TABS.register(
        "futons_tab",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("creativetab.futons_tab"))
            .icon(() -> ICON_ITEM.get().getDefaultInstance())
            .displayItems(
                (parameters, output) -> {
                    for (RegistryObject<Block> block : BlockRegistry.BLOCKS.getEntries()) {
                        output.accept(block.get().asItem().getDefaultInstance());
                    }
                    for (RegistryObject<Item> item : ItemRegistry.ITEMS.getEntries()) {
                        output.accept(item.get().getDefaultInstance());
                    }
                }
            )
            .build()
    );
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}