package application;

public class TicTacToe
{
	private int num;
	private char arr[][];
	public TicTacToe(int n)
	{
		num=n;
		arr=new char[num][num];
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr.length;j++)
			{
				arr[i][j]=' ';
			}
		}
	}
	
	public char[][] getArr()
	{
		return arr;
	}
	
	public boolean isGameOver()
	{
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr.length;j++)
			{
				if(arr[i][j]!=' ')
				{
					return false;
				}
			}
		}
		return true;
	}
	public boolean isHWin(char ch,int r)
	{
		for(int i=0;i<arr.length;i++)
		{
			if(arr[r][i]!=ch)
			{
				return false;
			}
		}
		return true;
	}
	public boolean isVWin(char ch,int c)
	{
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i][c]!=ch)
			{
				return false;
			}
		}
		return true;
	}
	public boolean isMDWin(char ch)
	{
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i][i]!=ch)
			{
				return false;
			}
		}
		return true;
	}
	public boolean isODWin(char ch)
	{
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i][arr.length-1-i]!=ch)
			{
				return false;
			}
		}
		return true;
	}
	public boolean isWin(int r,int c,char ch)
	{
		return isHWin(ch,r)||isVWin(ch,c)||isMDWin(ch)||isODWin(ch) ;
	}
	public boolean filling(char ch,int r,int c)
	{
		arr[r][c]=ch;
		return isWin(r,c,ch);
	}
	public boolean isAvailable(int r,int c)
	{
		if(arr[r][c]==' ')
		{
			return true;
		}
		return false;
	}
	public int arrL()
	{
		return arr.length-1;
	}
}
