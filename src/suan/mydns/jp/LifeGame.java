package suan.mydns.jp;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import processing.core.PApplet;

public class LifeGame extends PApplet
{
	public int[][][] stage;
	Random R = new Random();
	public int now = 0;
	public static int[] T = {800, 800, 50, 50};
	public boolean loop = false;

	public static void main(String[] args)
	{
		// TODO 自動生成されたメソッド・スタブ
		try
		{
			for(int i = 0; i < 4; i++)
			{
				T[i] = Integer.parseInt(args[i]);
			}
		}
		catch(Exception e)
		{
		}
		PApplet.main("suan.mydns.jp.LifeGame");
	}

	@Override
	public void settings()
	{
		size(T[0], T[1]);
	}

	@Override
	public void setup()
	{
		stage = new int[T[2]][T[3]][4];
		for(int i = 0; i < stage.length; i++)
		{
			for(int j = 0; j < stage[0].length; j++)
			{
				stage[i][j][0] = 0;
				stage[i][j][1] = 0;
				stage[i][j][2] = 0;
				stage[i][j][3] = 0;
			}
		}
		int A = R.nextInt(50) + 50;
		int B;
		int C;
		for(int i = 0; i < A; i++)
		{
			B = R.nextInt(stage.length);
			C = R.nextInt(stage[0].length);
			stage[B][C][0] = 1;
			stage[B][C][2] = 1;
		}
		Timer T2 = new Timer();
		T2.scheduleAtFixedRate(new RenderTask(), 0, 100);
	}

	class RenderTask extends TimerTask
	{
		@Override
		public void run()
		{
			// TODO 自動生成されたメソッド・スタブ
			if(enable)
			{
				for(int i = 0; i < stage.length; i++)
				{
					for(int j = 0; j < stage[0].length; j++)
					{
						int N = 0;
						for(int k = -1; k <= 1; k++)
						{
							for(int l = -1; l <= 1; l++)
							{
								if(k == 0 && l == 0) continue;
								try
								{
									if(stage[i+k][j+l][now] == 1) N++;
								}
								catch(Exception e)
								{
									if(loop)
									{
										if(i+k >= stage.length || i+k <= -1)
										{
											if(j+l >= stage[0].length || j+l <= -1)
											{
												if(stage[stage.length-Math.abs(i+k)][stage[0].length-Math.abs(j+l)][now] == 1) N++;
											}
											else
											{
												if(stage[stage.length-Math.abs(i+k)][j+l][now] == 1) N++;
											}
										}
										else
										{
											if(j+l >= stage[0].length || j+l <= -1)
											{
												if(stage[i+k][stage[0].length-Math.abs(j+l)][now] == 1) N++;
											}
											else
											{
												if(stage[i+k][j+l][now] == 1) N++;
											}
										}
									}
								}
							}
						}
						if(stage[i][j][now] == 1)
						{
							if(N == 2 || N == 3)
							{
								stage[i][j][now==0?1:0] = 1;
								stage[i][j][(now==0?1:0) + 2] = stage[i][j][now + 2] + 1;
							}
							else
							{
								stage[i][j][now==0?1:0] = 0;
								stage[i][j][(now==0?1:0) + 2] = 0;
							}
						}
						else
						{
							if(N == 3)
							{
								stage[i][j][now==0?1:0] = 1;
								stage[i][j][(now==0?1:0) + 2] = 1;
							}
							else
							{
								stage[i][j][now==0?1:0] = 0;
								stage[i][j][(now==0?1:0) + 2] = 0;
							}
						}
					}
				}
				now = now==0?1:0;
			}
		}
	}

	@Override
	public void draw()
	{
		for(int i = 0; i < stage.length; i++)
		{
			for(int j = 0; j < stage[0].length; j++)
			{
				if(stage[i][j][now + 2] == 0) fill(0xFF);
				else if(stage[i][j][now + 2] == 1) fill(0x00, 0xFF, 0x00);
				else if(stage[i][j][now + 2] == 2) fill(0x00, 0x00, 0xFF);
				else fill(0xFF, 0x00, 0x00);
				rect((width/stage.length)*i, (height/stage[0].length)*j, (width/stage.length), (height/stage[0].length));
			}
		}
	}

	@Override
	public void mousePressed()
	{
		int X = mouseX / (width/stage.length);
		int Y = mouseY / (height/stage[0].length);
		stage[X][Y][now] = 1;
		stage[X][Y][now + 2] = 1;
	}

	boolean enable = true;

	@Override
	public void keyPressed()
	{
		if(key == ' ')
		{
			enable = !enable;
		}
		else if(key == 'q')
		{
			for(int i = 0; i < stage.length; i++)
			{
				for(int j = 0; j < stage[0].length; j++)
				{
					stage[i][j][0] = 0;
					stage[i][j][1] = 0;
					stage[i][j][2] = 0;
					stage[i][j][3] = 0;
				}
			}
			now = 0;
		}
		else if(key == 'z')
		{
			for(int i = 0; i < stage.length; i++)
			{
				for(int j = 0; j < stage[0].length; j++)
				{
					stage[i][j][0] = 0;
					stage[i][j][1] = 0;
					stage[i][j][2] = 0;
					stage[i][j][3] = 0;
				}
			}
			int A = R.nextInt(50) + 50 * stage.length * stage[0].length / 1000;
			int B;
			int C;
			for(int i = 0; i < A; i++)
			{
				B = R.nextInt(stage.length);
				C = R.nextInt(stage[0].length);
				stage[B][C][0] = 1;
				stage[B][C][2] = 1;
			}
			now = 0;
		}
		else if(key == 'l')
		{
			loop = !loop;
		}
	}
}