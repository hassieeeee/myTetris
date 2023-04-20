import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class Game extends JFrame implements KeyListener{
	int x,y,tmp,judge,minostate=0,matu=0,holdmino=0,holdstate=0,linesum=0;
	int[] nextmino = {0,0,0};
	boolean t,game,s;
	int[] z = new int[22];
	int[][] fields = {
					 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//5
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//10
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//15
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//20
	};

	int[][] vfields = {
					 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//5
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//10
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//15
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
					,{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//20
	};


	Game(){
	addKeyListener(this);
	}

	int[][] getZahyo(){
		for(int i=0; i<20; i++){
			for(int j=0; j<10; j++){
				vfields[i][j] = fields[i+2][j];
			}
		}
		return vfields;
	}

	boolean down(){
		judge = 1;
		t = false;
		for(int i=21;i>0;i--){
			for(int j=0; j<10; j++){
				if(fields[i][j] >= 21 && fields[i][j] <= 37 && fields[i-1][j] >= 1 && fields[i-1][j] <= 17 ){
					judge = 0;	//ミノが引っかかったら止めるための変数
				}
			}
		}
		for(int j=0; j<10; j++){
			if(fields[21][j] >= 1 && fields[21][j] <= 17){
				judge = 0;
			}
		}
		if(judge == 0){
			for(int i=0; i<=21; i++){
				for(int j=0; j<10; j++){
					if(fields[i][j] >= 1 && fields[i][j] <= 17){
						fields[i][j] += 20;
					}
				}
			}
			return t;
		}
		for(int i=21;i>0;i--){
			for(int j=0; j<10; j++){
				if(fields[i][j] == 0 && fields[i-1][j] >= 1 && fields[i-1][j] <= 17){
					tmp = fields[i-1][j];
					fields[i][j] = tmp;
					fields[i-1][j] = 0;
					t = true;
				}
			}
		}
		return t;
	}

	void down2(int[] k){
		for(int i=0;i<=21;i++){
			if(k[i] == 1){
				for(int x=i; x>0; x--){
					for(int y=0; y<10; y++){
						fields[x][y] = fields[x-1][y];
					}
				}
				for(int j=0; j<10; j++){
					fields[0][j] = 0;
				}
			}
		}
	}

	void line(){
		for(int i=1;i<=21;i++){
			z[i] = 0;
		}
		for(int i=1;i<=21;i++){
			z[i] = 1;
			for(int j=0; j<10; j++){
				if(fields[i][j] == 0){
					z[i] = 0;
					break;
				}
			}
		}
		linesum = 0;
		for(int i=0;i<=21;i++){
			if(z[i] == 1){
				linesum = linesum+1;
				for(int j=0; j<10; j++){
					fields[i][j] = 0;
				}
			}
		}
		if(linesum >= 1 && linesum <= 3){
			Thread t = new SE1_3line();
			t.start();
		}
		else if(linesum == 4){
			Thread t = new SE4line();
			t.start();
		}
	}

	void summon(){
		Random rand = new Random();
    	int num = rand.nextInt(7) + 1;
		if(nextmino[0]==0){
			nextmino[0] = num;
			return;
		}else if(nextmino[1]==0){
			nextmino[1] = nextmino[0];
			nextmino[0] = num;
			return;
		}else if(nextmino[2]==0){
			nextmino[2]=nextmino[1];
			nextmino[1]=nextmino[0];
			nextmino[0]=num;
			return;
		}
		switch(nextmino[2]){
			case 1:
				fields[1][3]=11;
				fields[1][4]=1;
				fields[1][5]=1;
				fields[1][6]=1;
				break;
			case 2:
				fields[0][4]=2;
				fields[0][5]=2;
				fields[1][4]=2;
				fields[1][5]=2;
				break;
			case 3:
				fields[0][4]=3;
				fields[1][3]=3;
				fields[1][4]=13;
				fields[1][5]=3;
				break;
			case 4:
				fields[0][3]=4;
				fields[1][3]=4;
				fields[1][4]=14;
				fields[1][5]=4;
				break;
			case 5:
				fields[0][5]=5;
				fields[1][3]=5;
				fields[1][4]=15;
				fields[1][5]=5;
				break;
			case 6:
				fields[0][4]=6;
				fields[0][5]=6;
				fields[1][3]=6;
				fields[1][4]=16;
				break;
			case 7:
				fields[0][3]=7;
				fields[0][4]=7;
				fields[1][4]=17;
				fields[1][5]=7;
				break;
			default:
				break;
		}
		nextmino[2]=nextmino[1];
		nextmino[1]=nextmino[0];
		nextmino[0]=num;
		minostate = 0;
		holdstate = 0;
	}

	boolean gamecheck(){
		game = false;
		for(int j=0; j<10; j++){
			if(fields[1][j] != 0){
				game = true;
			}
		}
		return game;
	}

	int statechange(int lr, int minos){
		if(lr == 1) minos = minos+1;
		else minos = minos-1;
		if(minos<0) minos=minos+4;
		else if(minos>3) minos = minos-4;
		return minos;
	}
		

	int spin(int lr, int minos){
		int statex = minos;
		boolean s = false;//回転できたらtrueになる変数
		for(int i=0; i<22; i++){
			for(int j=0; j<10; j++){
				if(fields[i][j] >= 10 && fields[i][j] <= 17){
					switch(fields[i][j]){
						case 11:																					//ここからIミノ
							switch(minos){
								case 0:
								case 2:													//横から縦
									for(int y=0; y<=3; y++){
										if(i>=3 && fields[i-1][j+y]==0 && fields[i-2][j+y]==0 && fields[i-3][j+y]==0){
											for(int x=0; x<=3; x++){
												fields[i][j+x] = 0;
											}
											for(int x=0; x<=3; x++){
												if(x==0){
													fields[i-x][j+y]=11;
												}
												else{
													fields[i-x][j+y]=1;
												}
											}	
											s=true;
											break;
										}
									}
									break;
								case 1:													//縦から横
								case 3:
									for(int m=3; m>=0; m--){
										if(s) break;
										else if(m==3){
											for(int x=3; x>=0; x--){
												if(j>=3 && fields[i-x][j-3]==0 && fields[i-x][j-2]==0 && fields[i-x][j-1]==0 ){
													for(int y=0; y<=3; y++){
														fields[i-y][j]=0;
													}
													for(int y=0; y<=3; y++){
														if(y==0){
															fields[i-x][j-m+y]=11;
														}
														else{
															fields[i-x][j-m+y]=1;
														}
													}
													s=true;
													break;
												}
											}
										}
										else if(m==2){
											for(int x=3; x>=0; x--){
												if(j>=2 && j<=8 && fields[i-x][j-2]==0 && fields[i-x][j-1]==0 && fields[i-x][j+1]==0 ){
													for(int y=0; y<=3; y++){
														fields[i-y][j]=0;
													}
													for(int y=0; y<=3; y++){
														if(y==0){
															fields[i-x][j-m+y]=11;
														}
														else{
															fields[i-x][j-m+y]=1;
														}
													}
													s=true;
													break;
												}
											}
										}
										else if(m==1){
											for(int x=3; x>=0; x--){
												if(j>=1 && j<=7 && fields[i-x][j-1]==0 && fields[i-x][j+1]==0 && fields[i-x][j+2]==0 ){
													for(int y=0; y<=3; y++){
														fields[i-y][j]=0;
													}
													for(int y=0; y<=3; y++){
														if(y==0){
															fields[i-x][j-m+y]=11;
														}
														else{
															fields[i-x][j-m+y]=1;
														}
													}
													s=true;
													break;
												}
											}
										}
										else if(m==0){
											for(int x=3; x>=0; x--){
												if(j<=6 && fields[i-x][j+1]==0 && fields[i-x][j+2]==0 && fields[i-x][j+3]==0 ){
													for(int y=0; y<=3; y++){
														fields[i-y][j]=0;
													}
													for(int y=0; y<=3; y++){
														if(y==0){
															fields[i-x][j-m+y]=11;
														}
														else{
															fields[i-x][j-m+y]=1;
														}
													}
													s=true;
													break;
												}
											}
										}
									}
									break;
							}
							break;

						case 13:									//ここからTミノ
							switch(minos){
								case 0:
									if(i<=20 && fields[i+1][j] == 0){
										if(lr==1){
											fields[i][j-1]=0;
											fields[i+1][j]=3;
										}
										else{
											fields[i][j+1]=0;
											fields[i+1][j]=3;
										}
										s=true;
									}
									break;
								case 1:
									if(j>=1 && fields[i][j-1] == 0){
										if(lr==1){
											fields[i-1][j]=0;
											fields[i][j-1]=3;
										}
										else{
											fields[i+1][j]=0;
											fields[i][j-1]=3;
										}
										s=true;
									}
									break;
								case 2:
									if(i>=1 && fields[i-1][j] == 0){
										if(lr==1){
											fields[i][j+1]=0;
											fields[i-1][j]=3;
										}
										else{
											fields[i][j-1]=0;
											fields[i-1][j]=3;
										}
										s=true;
									}
									break;
								case 3:
									if(j<=8 && fields[i][j+1] == 0){
										if(lr==1){
											fields[i+1][j]=0;
											fields[i][j+1]=3;
										}
										else{
											fields[i-1][j]=0;
											fields[i][j+1]=3;
										}
										s=true;
									}
									break;
							}
							break;

						case 14:															//ここからJミノ
							if(lr == 1){
								switch(minos){
									case 0:
										if(i<=20){
											if(fields[i-1][j]==0 && fields[i-1][j+1]==0 && fields[i+1][j]==0){
												fields[i-1][j-1]=0;
												fields[i][j-1]=0;
												fields[i][j+1]=0;
												fields[i-1][j]=4;
												fields[i-1][j+1]=4;
												fields[i+1][j]=4;
												s=true;
											}
											else if(fields[i-1][j]==0 && fields[i+1][j-1]==0){
												fields[i][j+1]=0;
												fields[i][j]=0;
												fields[i][j-1]=14;
												fields[i-1][j]=4;
												fields[i+1][j-1]=4;
												s=true;
											}
										}
										break;
									case 1:
										if(j>=1){
											if(fields[i][j+1]==0 && fields[i+1][j+1]==0 && fields[i][j-1]==0){
												fields[i-1][j]=0;
												fields[i-1][j+1]=0;
												fields[i+1][j]=0;
												fields[i][j+1]=4;
												fields[i+1][j+1]=4;
												fields[i][j-1]=4;
												s=true;
											}
											else if(fields[i-1][j-1]==0 && fields[i][j+1]==0){
												fields[i+1][j]=0;
												fields[i][j]=0;
												fields[i-1][j]=14;
												fields[i-1][j-1]=4;
												fields[i][j+1]=4;
												s=true;
											}
										}
										break;
									case 2:
										if(i>=1){
											if(fields[i-1][j]==0 && fields[i+1][j]==0 && fields[i+1][j-1]==0){
												fields[i][j+1]=0;
												fields[i+1][j+1]=0;
												fields[i][j-1]=0;
												fields[i-1][j]=4;
												fields[i+1][j-1]=4;
												fields[i+1][j]=4;
												s=true;
											}
											else if(fields[i-1][j+1]==0 && fields[i+1][j]==0){
												fields[i][j-1]=0;
												fields[i][j]=0;
												fields[i][j+1]=14;
												fields[i-1][j+1]=4;
												fields[i+1][j]=4;
												s=true;
											}
										}
										break;
									case 3:
										if(j<=8){
											if(fields[i-1][j-1]==0 && fields[i][j-1]==0 && fields[i][j+1]==0){
												fields[i-1][j]=0;
												fields[i+1][j-1]=0;
												fields[i+1][j]=0;
												fields[i-1][j-1]=4;
												fields[i][j-1]=4;
												fields[i][j+1]=4;
												s=true;
											}
											else if(fields[i][j-1]==0 && fields[i+1][j+1]==0){
												fields[i-1][j]=0;
												fields[i][j]=0;
												fields[i+1][j]=14;
												fields[i][j-1]=4;
												fields[i+1][j+1]=4;
												s=true;
											}
										}
										break;
								}
							}
							else{
								switch(minos){
									case 0:
										if(i<=20){
											if(fields[i-1][j]==0 && fields[i+1][j-1]==0 && fields[i+1][j]==0){
												fields[i-1][j-1]=0;
												fields[i][j-1]=0;
												fields[i][j+1]=0;
												fields[i-1][j]=4;
												fields[i+1][j-1]=4;
												fields[i+1][j]=4;
												s=true;
											}
											else if(fields[i-1][j+1]==0 && fields[i+1][j]==0 && fields[i+1][j+1] == 0){
												fields[i-1][j-1]=0;
												fields[i][j-1]=0;
												fields[i][j]=0;
												fields[i][j+1]=14;
												fields[i-1][j+1]=4;
												fields[i+1][j+1]=4;
												fields[i+1][j]=4;
												s=true;
											}
										}
										break;
									case 1:
										if(j>=1){
											if(fields[i-1][j-1]==0 && fields[i][j-1]==0 && fields[i][j+1]==0){
												fields[i-1][j]=0;
												fields[i-1][j+1]=0;
												fields[i+1][j]=0;
												fields[i-1][j-1]=4;
												fields[i][j-1]=4;
												fields[i][j+1]=4;
												s=true;
											}
											else if(fields[i][j-1]==0 && fields[i+1][j-1]==0 && fields[i+1][j+1] == 0){
												fields[i-1][j]=0;
												fields[i-1][j+1]=0;
												fields[i][j]=0;
												fields[i+1][j]=14;
												fields[i][j-1]=4;
												fields[i+1][j-1]=4;
												fields[i+1][j+1]=4;
												s=true;
											}
										}
										break;
									case 2:
										if(i>=1){
											if(fields[i-1][j]==0 && fields[i-1][j+1]==0 && fields[i+1][j]==0){
												fields[i][j-1]=0;
												fields[i][j+1]=0;
												fields[i+1][j+1]=0;
												fields[i-1][j]=4;
												fields[i-1][j+1]=4;
												fields[i+1][j]=4;
												s=true;
											}
											else if(fields[i-1][j-1]==0 && fields[i-1][j]==0 && fields[i+1][j-1] == 0){
												fields[i][j]=0;
												fields[i][j+1]=0;
												fields[i+1][j+1]=0;
												fields[i][j-1]=14;
												fields[i-1][j-1]=4;
												fields[i-1][j]=4;
												fields[i+1][j-1]=4;
												s=true;
											}
										}
										break;
									case 3:
										if(j<=8){
											if(fields[i][j-1]==0 && fields[i][j+1]==0 && fields[i+1][j+1]==0){
												fields[i-1][j]=0;
												fields[i+1][j-1]=0;
												fields[i+1][j]=0;
												fields[i][j-1]=4;
												fields[i][j+1]=4;
												fields[i+1][j+1]=4;
												s=true;
											}
											else if(fields[i-1][j-1]==0 && fields[i-1][j+1]==0 && fields[i][j+1] == 0){
												fields[i][j]=0;
												fields[i+1][j-1]=0;
												fields[i+1][j]=0;
												fields[i-1][j]=14;
												fields[i-1][j-1]=4;
												fields[i-1][j+1]=4;
												fields[i][j+1]=4;
												s=true;
											}
										}
										break;
								}
							}
							break;

						case 15:											//ここからLミノ
							if(lr == 1){
								switch(minos){
									case 0:
										if(i<=20){
											if(fields[i-1][j]==0 && fields[i+1][j]==0 && fields[i+1][j+1]==0){
												fields[i][j-1]=0;
												fields[i][j+1]=0;
												fields[i-1][j+1]=0;
												fields[i-1][j]=5;
												fields[i+1][j]=5;
												fields[i+1][j+1]=5;
												s=true;
											}
											else if(fields[i-1][j-1]==0 && fields[i+1][j-1]==0 && fields[i+1][j] == 0){
												fields[i][j]=0;
												fields[i][j+1]=0;
												fields[i-1][j+1]=0;
												fields[i][j-1]=15;
												fields[i-1][j-1]=5;
												fields[i+1][j-1]=5;
												fields[i+1][j]=5;
												s=true;
											}
										}
										break;
									case 1:
										if(j>=1){
											if(fields[i][j-1]==0 && fields[i][j+1]==0 && fields[i+1][j-1]==0){
												fields[i-1][j]=0;
												fields[i+1][j]=0;
												fields[i+1][j+1]=0;
												fields[i][j-1]=5;
												fields[i][j+1]=5;
												fields[i+1][j-1]=5;
												s=true;
											}
											else if(fields[i-1][j-1]==0 && fields[i-1][j+1]==0 && fields[i][j-1] == 0){
												fields[i][j]=0;
												fields[i+1][j]=0;
												fields[i+1][j+1]=0;
												fields[i-1][j]=15;
												fields[i-1][j-1]=5;
												fields[i-1][j+1]=5;
												fields[i][j-1]=5;
												s=true;
											}
										}
										break;
									case 2:
										if(i>=1){
											if(fields[i-1][j-1]==0 && fields[i-1][j]==0 && fields[i+1][j]==0){
												fields[i][j-1]=0;
												fields[i][j+1]=0;
												fields[i+1][j-1]=0;
												fields[i-1][j-1]=5;
												fields[i-1][j]=5;
												fields[i+1][j]=5;
												s=true;
											}
											else if(fields[i-1][j]==0 && fields[i-1][j+1]==0 && fields[i+1][j+1] == 0){
												fields[i][j]=0;
												fields[i][j-1]=0;
												fields[i+1][j-1]=0;
												fields[i][j+1]=15;
												fields[i-1][j]=5;
												fields[i-1][j+1]=5;
												fields[i+1][j+1]=5;
												s=true;
											}
										}
										break;
									case 3:
										if(j<=8){
											if(fields[i-1][j+1]==0 && fields[i][j-1]==0 && fields[i][j+1]==0){
												fields[i-1][j-1]=0;
												fields[i-1][j]=0;
												fields[i+1][j]=0;
												fields[i-1][j+1]=5;
												fields[i][j-1]=5;
												fields[i][j+1]=5;
												s=true;
											}
											else if(fields[i][j+1]==0 && fields[i+1][j-1]==0 && fields[i+1][j+1] == 0){
												fields[i-1][j-1]=0;
												fields[i-1][j]=0;
												fields[i][j]=0;
												fields[i+1][j]=15;
												fields[i][j+1]=5;
												fields[i+1][j-1]=5;
												fields[i+1][j+1]=5;
												s=true;
											}
										}
										break;
								}
							}
							else{
								switch(minos){
									case 0:
										if(i<=20){
											if(fields[i-1][j-1]==0 && fields[i-1][j]==0 && fields[i+1][j]==0){
												fields[i-1][j+1]=0;
												fields[i][j-1]=0;
												fields[i][j+1]=0;
												fields[i-1][j-1]=5;
												fields[i-1][j]=5;
												fields[i+1][j]=5;
												s=true;
											}
											else if(fields[i-1][j]==0 && fields[i+1][j+1]==0){
												fields[i][j-1]=0;
												fields[i][j]=0;
												fields[i][j+1]=15;
												fields[i-1][j]=5;
												fields[i+1][j+1]=5;
												s=true;
											}
										}
										break;
									case 1:
										if(j>=1){
											if(fields[i-1][j+1]==0 && fields[i][j-1]==0 && fields[i][j+1]==0){
												fields[i-1][j]=0;
												fields[i+1][j]=0;
												fields[i+1][j+1]=0;
												fields[i-1][j+1]=5;
												fields[i][j-1]=5;
												fields[i][j+1]=5;
												s=true;
											}
											else if(fields[i+1][j-1]==0 && fields[i][j+1]==0){
												fields[i-1][j]=0;
												fields[i][j]=0;
												fields[i+1][j]=15;
												fields[i+1][j-1]=5;
												fields[i][j+1]=5;
												s=true;
											}
										}
										break;
									case 2:
										if(i>=1){
											if(fields[i-1][j]==0 && fields[i+1][j]==0 && fields[i+1][j+1]==0){
												fields[i][j-1]=0;
												fields[i][j+1]=0;
												fields[i+1][j-1]=0;
												fields[i-1][j]=5;
												fields[i+1][j]=5;
												fields[i+1][j+1]=5;
												s=true;
											}
											else if(fields[i-1][j-1]==0 && fields[i+1][j]==0){
												fields[i][j]=0;
												fields[i][j+1]=0;
												fields[i][j-1]=15;
												fields[i-1][j-1]=5;
												fields[i+1][j]=5;
												s=true;
											}
										}
										break;
									case 3:
										if(j<=8){
											if(fields[i][j-1]==0 && fields[i][j+1]==0 && fields[i+1][j-1]==0){
												fields[i-1][j-1]=0;
												fields[i-1][j]=0;
												fields[i+1][j]=0;
												fields[i][j-1]=5;
												fields[i][j+1]=5;
												fields[i+1][j-1]=5;
												s=true;
											}
											else if(fields[i][j-1]==0 && fields[i-1][j+1]==0){
												fields[i][j]=0;
												fields[i+1][j]=0;
												fields[i-1][j]=15;
												fields[i][j-1]=5;
												fields[i-1][j+1]=5;
												s=true;
											}
										}
										break;
								}
							}
							break;

						case 16:																				//ここからSミノ
							switch(minos){
								case 0:
								case 2:
									if(i>=2){
										if(fields[i][j+1]==0 && fields[i-2][j]==0){
											fields[i][j]=0;
											fields[i][j-1]=0;
											fields[i-1][j]=16;
											fields[i-2][j]=6;
											fields[i][j+1]=6;
											s=true;
										}
										else if(fields[i-2][j-1]==0 && fields[i-1][j-1]==0){
											fields[i][j]=6;
											fields[i-1][j+1]=0;
											fields[i][j-1]=0;
											fields[i-1][j-1]=16;
											fields[i-2][j-1]=6;
											s=true;
										}
									}
									break;
								case 1:
								case 3:
									if(j<=7){
										if(fields[i-1][j+1]==0 && fields[i-1][j+2]==0){
											fields[i-1][j]=0;
											fields[i+1][j+1]=0;
											fields[i][j]=6;
											fields[i][j+1]=16;
											fields[i-1][j+1]=6;
											fields[i-1][j+2]=6;

											s=true;
										}
									}
									else if(j==8){
										if(fields[i-1][j+1]==0 && fields[i][j-1]==0){
											fields[i][j+1]=0;
											fields[i+1][j+1]=0;
											fields[i-1][j+1]=6;
											fields[i][j-1]=6;
											s=true;
										}/*
										else if(fields[i+1][j]==0 && fields[i+1][j-1]==0){
											fields[i-1][j]=0;
											fields[i+1][j+1]=0;
											fields[i+1][j]=16;
											fields[i][j]=6;
											fields[i+1][j-1]=6;
											s=true;
										}*/
									}
									break;
							}
							break;
						case 17:
							switch(minos){
								case 0:
								case 2:
									if(i>=2){
										if(fields[i-2][j+1]==0 && fields[i-1][j+1]==0){
											fields[i-1][j-1]=0;
											fields[i][j+1]=0;
											fields[i-1][j]=17;
											fields[i-2][j+1]=7;
											fields[i-1][j+1]=7;
											s=true;
										}
										else if(fields[i-2][j]==0 && fields[i][j-1]==0){
											fields[i][j]=0;
											fields[i][j+1]=0;
											fields[i-1][j-1]=17;
											fields[i-2][j]=7;
											fields[i][j-1]=7;
											s=true;
										}
									}
									break;
								case 1:
								case 3:
									if(j>=1){
										if(fields[i-1][j-1]==0 && fields[i-1][j]==0){
											fields[i-1][j+1]=0;
											fields[i+1][j]=0;
											fields[i-1][j-1]=7;
											fields[i-1][j]=7;
											s=true;
										}
									}
									else{
										if(fields[i-1][j]==0 && fields[i][j+2]==0){
											fields[i][j]=0;
											fields[i+1][j]=0;
											fields[i-1][j]=7;
											fields[i][j+2]=7;
											fields[i][j+1]=17;
											s=true;
										}
									}
									break;
							}

						default:
							break;
					}
				}
			}
		}
		if(s) statex = statechange(lr, minos);
		return statex;
	}

	int matimasu(){
		if(matu==1){
			matu=0;
			return 1;
		}else{
			return 0;
		}
	}

	void hold(){
		int nextholdmino = 0;
		boolean check = false;
		if(holdstate==0){
			for(int i=0; i<22; i++){
				for(int j=0; j<10; j++){
					if(fields[i][j]>=1 && fields[i][j]<=7){
						nextholdmino = fields[i][j];
						check = true;
					}
					if(fields[i][j]>=1 && fields[i][j]<=17){
						fields[i][j]=0;
					}
				}
			}
		}
		if(check){
			holdsummon(holdmino);
			holdmino = nextholdmino;
			holdstate = 1;
			Thread t = new SEhold();
			t.start();
		}
	}

	void holdsummon(int mino){
		switch(mino){
			case 1:
				fields[1][3]=11;
				fields[1][4]=1;
				fields[1][5]=1;
				fields[1][6]=1;
				break;
			case 2:
				fields[0][4]=2;
				fields[0][5]=2;
				fields[1][4]=2;
				fields[1][5]=2;
				break;
			case 3:
				fields[0][4]=3;
				fields[1][3]=3;
				fields[1][4]=13;
				fields[1][5]=3;
				break;
			case 4:
				fields[0][3]=4;
				fields[1][3]=4;
				fields[1][4]=14;
				fields[1][5]=4;
				break;
			case 5:
				fields[0][5]=5;
				fields[1][3]=5;
				fields[1][4]=15;
				fields[1][5]=5;
				break;
			case 6:
				fields[0][4]=6;
				fields[0][5]=6;
				fields[1][3]=6;
				fields[1][4]=16;
				break;
			case 7:
				fields[0][3]=7;
				fields[0][4]=7;
				fields[1][4]=17;
				fields[1][5]=7;
				break;
			default:
				break;
		}
		minostate = 0;
	}


//minostate()をわすれずに！

@Override
	public void keyPressed(KeyEvent e) {
		switch ( e.getKeyCode() ) {
			case KeyEvent.VK_D:								//右移動
			//Dキー
				judge = 1;
				for(int i=0; i<22; i++){
					for(int j=8; j>=0; j--){
						if(fields[i][j] >= 1 && fields[i][j] <= 17){
							if(fields[i][j+1] != 0){
								judge = 0;
							}
							break;
						}
					}
				}
				if(judge == 0) break;
				matu=1;
				for(int j=9; j>0; j--){
					for(int i=0; i<22; i++){
						if(fields[i][j] == 0 && fields[i][j-1] >= 1 && fields[i][j-1] <= 17){
							fields[i][j] = fields[i][j-1];
							fields[i][j-1] = 0;
						}
					}
				}
				Thread t = new SEselect();
				t.start();
				break;

			case KeyEvent.VK_A:								//左移動
			//Aキー
				judge = 1;
				for(int i=0; i<22; i++){
					for(int j=1; j<9; j++){
						if(fields[i][j] >= 1 && fields[i][j] <= 17){
							if(fields[i][j-1] != 0){
								judge = 0;
							}
							break;
						}
					}
				}
				if(judge == 0) break;
				matu=1;
				for(int j=0; j<9; j++){
					for(int i=0; i<22; i++){
						if(fields[i][j] == 0 && fields[i][j+1] >= 1 && fields[i][j+1] <= 17){
							fields[i][j] = fields[i][j+1];
							fields[i][j+1] = 0;
						}
					}
				}
				Thread u = new SEselect();
				u.start();
				break;

			case KeyEvent.VK_S:								//下移動
			//Sキー
				down();
				break;

			case KeyEvent.VK_W:								//ハードドロップ
			//Wキー
				boolean a;
				while(true){
					a = down();
					if(a == false){
						break;
					}
				}
				break;
			case KeyEvent.VK_RIGHT:								//右回転
			//右矢印キー
				minostate = spin(1, minostate);	
				matu=1;
				Thread v = new SEselect();
				v.start();
				break;

			case KeyEvent.VK_LEFT:								//左回転
			//左矢印キー
				minostate = spin(0, minostate);
				matu=1;
				Thread w = new SEselect();
				w.start();
				break;

			case KeyEvent.VK_SPACE:								//ホールド
			//スペースキー
				hold();	
				break;
		}
	}

@Override
	public void keyTyped(KeyEvent e) {
		//使用しないので空にしておきます。
	}

@Override
	public void keyReleased(KeyEvent e) {
	}

}