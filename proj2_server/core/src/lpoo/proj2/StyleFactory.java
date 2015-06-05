package lpoo.proj2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class StyleFactory
{
	private static final TextureAtlas StyleAtlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private static final Skin StyleSkin = new Skin(Gdx.files.internal("menu/menu.json"), StyleAtlas);
	public static final TextButtonStyle BlueButton = new TextButtonStyle(StyleSkin.get("default", TextButtonStyle.class));
	public static final TextButtonStyle MenuButton = new TextButtonStyle(StyleSkin.get("menuLabel", TextButtonStyle.class));
	public static final TextButtonStyle GreenButton = new TextButtonStyle(StyleSkin.get("menuGreen", TextButtonStyle.class));
	public static final TextButtonStyle RedButton = new TextButtonStyle(StyleSkin.get("menuRed", TextButtonStyle.class));
	public static final TextButtonStyle YellowButton = new TextButtonStyle(StyleSkin.get("menuYellow", TextButtonStyle.class));
	public static final TextButtonStyle PurpleButton = new TextButtonStyle(StyleSkin.get("menuPurple", TextButtonStyle.class));
	public static final TextButtonStyle OrangeButton = new TextButtonStyle(StyleSkin.get("menuOrange", TextButtonStyle.class));
	public static final TextButtonStyle ToggleButton = new TextButtonStyle(StyleSkin.get("toggle", TextButtonStyle.class));
	public static final LabelStyle GradientLabel = new LabelStyle(StyleSkin.get("gradientLabel", LabelStyle.class));
	public static final LabelStyle SmallLabel = new LabelStyle(StyleSkin.get("smallLabel", LabelStyle.class));
	public static final LabelStyle TitleLabel = new LabelStyle(StyleSkin.get("default", LabelStyle.class));
	public static final LabelStyle BlueLabel = new LabelStyle(StyleSkin.get("blueLabel", LabelStyle.class));
	public static final LabelStyle GreenLabel = new LabelStyle(StyleSkin.get("greenLabel", LabelStyle.class));
	public static final LabelStyle RedLabel = new LabelStyle(StyleSkin.get("redLabel", LabelStyle.class));
	public static final LabelStyle YellowLabel = new LabelStyle(StyleSkin.get("yellowLabel", LabelStyle.class));
	public static final LabelStyle PurpleLabel = new LabelStyle(StyleSkin.get("purpleLabel", LabelStyle.class));
	public static final LabelStyle OrangeLabel = new LabelStyle(StyleSkin.get("orangeLabel", LabelStyle.class));
	public static final CheckBoxStyle DefaultCheckbox = new CheckBoxStyle(StyleSkin.get("default", CheckBoxStyle.class));
	public static final SliderStyle DefaultSlider = new SliderStyle(StyleSkin.get("default-horizontal", SliderStyle.class));
	public static final ImageButtonStyle VolumeButton = new ImageButtonStyle(StyleSkin.get("default", ImageButtonStyle.class));

	public static final TextButtonStyle ButtonStyles[] =
	{
		BlueButton,
		GreenButton,
		RedButton,
		YellowButton,
		PurpleButton,
		OrangeButton
	};

	public static final LabelStyle LabelStyles[] =
	{
		BlueLabel,
		GreenLabel,
		RedLabel,
		YellowLabel,
		PurpleLabel,
		OrangeLabel
	};
}