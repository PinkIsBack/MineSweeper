package mines;

import java.lang.Math;

public class Mines {
	 
	public class Tile{
		private boolean mine=false,open=false,flag=false;
		private int Neighbor=0;
		
		public Tile(boolean mine,boolean open,boolean flag,int Neighbor) {
			this.mine=mine;
			this.open=open;
			this.flag=flag;
			this.Neighbor=Neighbor;
		}
		
		public boolean isMine() {
			return mine;
		}
		public void setMine(boolean mine) {
			this.mine= mine;
		}
		public boolean isOpen() {
			return open;
		}
		public void setOpen(boolean open) {
			this.open = open;
		}
		public boolean isFlag() {
			return flag;
		}
		public void setFlag(boolean flag) {
			if(this.open==false)
				this.flag = flag;
			else System.out.println("ERROR");
		}
		public int getNeighbor() {
			return Neighbor;
		}
		public void setNeighbor(int Neighbor) {
			this.Neighbor += Neighbor;
		}
	}
	
	private int height,width,numMines;
	private Tile arr[][];
	private boolean showAll=false;
	public Mines() {};
	public Mines(int height, int width, int numMines) {
		this.height=height;
		this.width=width;
		this.numMines=numMines;
		this.arr=new Tile[this.height][this.width];
		
		for (int i = 0; i < this.height; i++)
			for (int j = 0; j < this.width; j++)
				this.arr[i][j]=new Tile(false,false,false,0);
		
		for (int i = 0; i < numMines; i++) { 
            int xrand = (int)(Math.random() * height-1) ;
            int yrand = (int)(Math.random() * width-1) ;
            if(this.arr[xrand][yrand].isMine()==true)
            	i--;
            else this.arr[xrand][yrand].setMine(true);
        }
		
		int count=0;
		
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if(this.arr[i][j].isMine()==true)continue;
				else{
					if(j-1>=0 && j-1<this.width) {
						if(this.arr[i][j-1].isMine()==true)count++;
					}
					if(j+1>=0 && j+1<this.width) {
						if(this.arr[i][j+1].isMine()==true)count++;
					}
					if(i-1>=0 && i-1<this.height) {
						if(this.arr[i-1][j].isMine()==true)count++;
						if(j-1>=0 && j-1<this.width) {
							if(this.arr[i-1][j-1].isMine()==true)count++;
						}
						if(j+1>=0 && j+1<this.width) {
							if(this.arr[i-1][j+1].isMine()==true)count++;
						}
					}
					if(i+1>=0 && i+1<this.height) {
						if(this.arr[i+1][j].isMine()==true)count++;
						if(j-1>=0 && j-1<this.width) {
							if(this.arr[i+1][j-1].isMine()==true)count++;
						}
						if(j+1>=0 && j+1<this.width) {
							if(this.arr[i+1][j+1].isMine()==true)count++;
						}
					}
				}
				this.arr[i][j].setNeighbor(count);
				count=0;
	        }
        }
		
	}
	public int getNumMines() {
		return numMines;
	}
	public void setNumMines(int numMines) {
		this.numMines = numMines;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public boolean addMine(int i, int j) {
		if(this.arr[i][j].isMine()==true)return false;
        else {
        	this.arr[i][j].setMine(true);
        	if(j-1>=0 && j-1<this.width) {
				this.arr[i][j-1].setNeighbor(1);
			}
			if(j+1>=0 && j+1<this.width) {
				this.arr[i][j+1].setNeighbor(1);
			}
			if(i-1>=0 && i-1<this.height) {
				this.arr[i-1][j].setNeighbor(1);
				if(j-1>=0 && j-1<this.width) {
					this.arr[i-1][j-1].setNeighbor(1);
				}
				if(j+1>=0 && j+1<this.width) {
					this.arr[i-1][j+1].setNeighbor(1);
				}
			}
			if(i+1>=0 && i+1<this.height) {
				this.arr[i+1][j].setNeighbor(1);
				if(j-1>=0 && j-1<this.width) {
					this.arr[i+1][j-1].setNeighbor(1);
				}
				if(j+1>=0 && j+1<this.width) {
					this.arr[i+1][j+1].setNeighbor(1);
				}
			}
        	return true;
        }
	}
	
	public boolean open(int i, int j) {
		if(this.arr[i][j].isFlag()==false)
			if(this.arr[i][j].isOpen()==false) {
				this.arr[i][j].setOpen(true);
					if(this.arr[i][j].getNeighbor()==0) {
						if(j-1>=0 && j-1<this.width) {
							open(i,j-1);
						}
						if(j+1>=0 && j+1<this.width) {
							open(i,j+1);
						}
						if(i-1>=0 && i-1<this.height) {
							open(i-1,j);
							if(j-1>=0 && j-1<this.width) {
								open(i-1,j-1);
							}
							if(j+1>=0 && j+1<this.width) {
								open(i-1,j+1);
							}
						}
						if(i+1>=0 && i+1<this.height) {
							open(i+1,j);
							if(j-1>=0 && j-1<this.width) {
								open(i+1,j-1);
							}
							if(j+1>=0 && j+1<this.width) {
								open(i+1,j+1);
							}
						}
					}
					return true;
				}
			else
				return false;
		else
			return false;
	}
	
	public void toggleFlag(int x, int y) {
		if(this.arr[x][y].isFlag()==false)this.arr[x][y].setFlag(true);
		else this.arr[x][y].setFlag(false);
	}
	
	public boolean isDone() {
		for(int i=0;i<this.height;i++) 
			for(int j=0;j<this.width;j++) 
				if(this.arr[i][j].isMine()==false) 
					if(this.arr[i][j].isOpen()==false)
						return false;
		return true;
	}
	
	public String get(int i, int j) {
		if(this.arr[i][j].isOpen()==false && this.showAll==false) {
			if(this.arr[i][j].isFlag()==true)return "F";
			else return ".";
		}
		else {
			if(this.arr[i][j].isMine()==true)return "X";
			else {
				if(this.arr[i][j].getNeighbor()==0)return " ";
				else return ""+this.arr[i][j].getNeighbor();
			}
		}
			
	}
	
	public void setShowAll(boolean showAll){
		this.showAll=showAll;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<this.height;i++) {
			for(int j=0;j<this.width;j++) {
				sb.append(this.get(i,j));
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
