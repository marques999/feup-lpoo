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
	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("menu/menu.atlas"));
	private static final Skin skin = new Skin(Gdx.files.internal("menu/menu.json"), atlas);
	public static final TextButtonStyle BlueButton = new TextButtonStyle(skin.get("default", TextButtonStyle.class));
	public static final TextButtonStyle MenuButton = new TextButtonStyle(skin.get("menuLabel", TextButtonStyle.class));
	public static final TextButtonStyle GreenButton = new TextButtonStyle(skin.get("menuGreen", TextButtonStyle.class));
	public static final TextButtonStyle RedButton = new TextButtonStyle(skin.get("menuRed", TextButtonStyle.class));
	public static final TextButtonStyle YellowButton = new TextButtonStyle(skin.get("menuYellow", TextButtonStyle.class));
	public static final TextButtonStyle PurpleButton = new TextButtonStyle(skin.get("menuPurple", TextButtonStyle.class));
	public static final TextButtonStyle OrangeButton = new TextButtonStyle(skin.get("menuOrange", TextButtonStyle.class));
	public static final TextButtonStyle ToggleButton = new TextButtonStyle(skin.get("toggle", TextButtonStyle.class));
	public static final LabelStyle GradientLabel = new LabelStyle(skin.get("gradientLabel", LabelStyle.class));
	public static final LabelStyle SmallLabel = new LabelStyle(skin.get("smallLabel", LabelStyle.class));
	public static final LabelStyle TitleLabel = new LabelStyle(skin.get("default", LabelStyle.class));
	public static final LabelStyle BlueLabel = new LabelStyle(skin.get("blueLabel", LabelStyle.class));
	public static final LabelStyle GreenLabel = new LabelStyle(skin.get("greenLabel", LabelStyle.class));
	public static final LabelStyle RedLabel = new LabelStyle(skin.get("redLabel", LabelStyle.class));
	public static final LabelStyle YellowLabel = new LabelStyle(skin.get("yellowLabel", LabelStyle.class));
	public static final LabelStyle PurpleLabel = new LabelStyle(skin.get("purpleLabel", LabelStyle.class));
	public static final LabelStyle OrangeLabel = new LabelStyle(skin.get("orangeLabel", LabelStyle.class));
	public static final CheckBoxStyle DefaultCheckbox = new CheckBoxStyle(skin.get("default", CheckBoxStyle.class));
	public static final SliderStyle DefaultSlider = new SliderStyle(skin.get("default-horizontal", SliderStyle.class));
	public static final ImageButtonStyle VolumeButton = new ImageButtonStyle(skin.get("default", ImageButtonStyle.class));

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