package net.minecraft.src;

public class ModelRumba extends ModelBase {
	public ModelRenderer box;

	public ModelRumba() {
		// constructor:
		box = new ModelRenderer(this, 0, 0);
		box.addBox(0F, 0F, 0F, 16, 4, 16);

		// render:
		box.render(5f);

	}
}
