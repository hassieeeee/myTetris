import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

//JFrame を継承
public class MyTetris extends JFrame {
    public static void main(String args[]){
		Thread t = new SoundPlay();
		t.start();
        MyTetris frame = new MyTetris("ゲーム画面");//引数はWindow Title
        //frame.setVisible(true);
		t.interrupt();
    }

    //constructor. フレームの設定関係を行う
    MyTetris(String title){
        setTitle(title);
        setSize(600,500);
        setLocationRelativeTo(null);//初期画面表示位置を中央に
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//CLOSEでプログラム終了

        Container PCP = getContentPane();//getContentPane()はJFrameクラスに定義されている

		JLayeredPane CP = new JLayeredPane();
		CP.setLayout(null);//レイアウトマネージャを停止

		//背景画像を貼るためのパネル
		BGimage background = new BGimage();
		background.setBounds(0,0,600,500);
		CP.add(background);
		CP.setLayer(background, 1);

		//holdを描画するためのパネルを貼るパネルの作成
		JPanel phold = new JPanel();
		phold.setBounds(110,35,70,49);
		phold.setBackground(Color.black);
		phold.setLayout(null);
		CP.add(phold);
		CP.setLayer(phold, 2);

		//文字ラベル
		JLabel holdLabel = new JLabel("HOLD", SwingConstants.CENTER);
		holdLabel.setForeground(Color.white);
		holdLabel.setFont(new Font("Arial", Font.BOLD, 17));
		holdLabel.setBounds(110,20,70,15);
		CP.add(holdLabel);
		CP.setLayer(holdLabel, 2);

		JLabel nextLabel = new JLabel("NEXT", SwingConstants.CENTER);
		nextLabel.setForeground(Color.white);
		nextLabel.setFont(new Font("Arial", Font.BOLD, 17));
		nextLabel.setBounds(420,20,70,15);
		CP.add(nextLabel);
		CP.setLayer(nextLabel, 2);

		//キー説明

		JPanel PEX = new JPanel();
		Color PEXcol = new Color(0,0,0,140);
		PEX.setBackground(PEXcol);
		PEX.setBounds(415,250,155,120);
		CP.add(PEX);
		CP.setLayer(PEX, 2);

		JLabel EXLabel = new JLabel("<html><body>A：左移動<br/>D：右移動<br/>W：ハードドロップ<br/>S：落下<br/>←：左回転<br/>→：右回転<br/>スペースキー：ホールド</body></html>");
		EXLabel.setForeground(Color.white);
		EXLabel.setFont(new Font("Arial", Font.BOLD, 13));
		EXLabel.setBounds(420,250,150,120);
		CP.add(EXLabel);
		CP.setLayer(EXLabel, 3);

		//スコアパネル

		JPanel PSC = new SCPanel();
		PSC.setBounds(50,200,120,120);
		PSC.setOpaque(false);
		CP.add(PSC);
		CP.setLayer(PSC, 2);

		JLabel SCLabel = new JLabel("SCORE", SwingConstants.CENTER);
		SCLabel.setForeground(Color.white);
		SCLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		SCLabel.setBounds(50,200,120,40);
		CP.add(SCLabel);
		CP.setLayer(SCLabel, 3);

		JLabel SCORE = new JLabel("0", SwingConstants.CENTER);
		SCORE.setForeground(Color.white);
		SCORE.setFont(new Font("Arial", Font.BOLD, 20));
		SCORE.setBounds(50,240,120,40);
		CP.add(SCORE);
		CP.setLayer(SCORE, 3);

		JLabel SCplus = new JLabel("", SwingConstants.CENTER);
		SCplus.setForeground(Color.white);
		SCplus.setFont(new Font("Arial", Font.PLAIN, 18));
		SCplus.setBounds(50,280,120,20);
		CP.add(SCplus);
		CP.setLayer(SCplus, 3);

		JLabel SCpluslog = new JLabel("", SwingConstants.CENTER);
		SCpluslog.setForeground(Color.white);
		SCpluslog.setFont(new Font("Arial", Font.PLAIN, 12));
		SCpluslog.setBounds(50,300,120,20);
		CP.add(SCpluslog);
		CP.setLayer(SCpluslog, 3);

		//nextを描画するためのパネルを貼るパネルの作成
		JPanel pnext1 = new JPanel();
		JPanel pnext2 = new JPanel();
		JPanel pnext3 = new JPanel();
		pnext1.setBounds(420,35,70,49);
		pnext2.setBounds(420,100,70,49);
		pnext3.setBounds(420,165,70,49);
		pnext1.setBackground(Color.black);
		pnext2.setBackground(Color.black);
		pnext3.setBackground(Color.black);
		pnext1.setLayout(null);
		pnext2.setLayout(null);
		pnext3.setLayout(null);
		CP.add(pnext1);
		CP.add(pnext2);
		CP.add(pnext3);
		CP.setLayer(pnext1, 2);
		CP.setLayer(pnext2, 2);
		CP.setLayer(pnext3, 2);

		//Mainパネルの枠の作成、フレームへのセット
        JPanel PMP = new JPanel();
		PMP.setBounds(197,32,206,406);
        CP.add(PMP);
		CP.setLayer(PMP, 2);

        //Mainパネルの作成、フレームへのセット
        JPanel MP = new JPanel();
		MP.setBounds(200,35,200,400);
		MP.setBackground(Color.black);
		MP.setLayout(new GridLayout(20, 10));
        CP.add(MP);
		CP.setLayer(MP, 3);
        //CP.remove(MP);//フレームを外す
        //addKeyListener(MP);//KeyListenerをフレームにセット
        //CP.removeKeyListener(MP);//KeyListenerを外す

		PCP.add(CP);

		setVisible(true);

		Game GM = new Game();
		addKeyListener(GM);

		boolean t,u,game=false;
		Color col;
		int[] z = new int[22];
		int state=1;
		int n=0;
		int k = 1;
		int score = 0;
		int scoreplus = 0;
		int renzoku = 0;

		GM.summon();
		GM.summon();
		GM.summon();

		while(true){
			int fields[][] = GM.getZahyo();

			phold.removeAll();

			//holdパネルの作成
			Minoimage hold = new Minoimage(GM.holdmino);
			hold.setBounds(0,0,70,49);
			phold.add(hold);

			pnext1.removeAll();
			pnext2.removeAll();
			pnext3.removeAll();

			//nextパネルの作成
			Minoimage next1 = new Minoimage(GM.nextmino[2]);
			Minoimage next2 = new Minoimage(GM.nextmino[1]);
			Minoimage next3 = new Minoimage(GM.nextmino[0]);
			next1.setBounds(0,0,70,49);
			next2.setBounds(0,0,70,49);
			next3.setBounds(0,0,70,49);
			pnext1.add(next1);
			pnext2.add(next2);
			pnext3.add(next3);

			MP.removeAll();

			MinoPanel[][] mino = new MinoPanel[20][10];
			
			for(int i=0; i<20; i++){	//ミノの色付け
				for(int j=0; j<10; j++){
					switch(fields[i][j]){
						case 1:
						case 11:
						case 21:
						case 31:
							col = Color.cyan;
							k=1;
							break;

						case 2:
						case 12:
						case 22:
						case 32:
							col = Color.yellow;
							k=2;
							break;

						case 3:
						case 13:
						case 23:
						case 33:
							col = Color.magenta;
							k=3;
							break;

						case 4:
						case 14:
						case 24:
						case 34:
							col = Color.blue;
							k=4;
							break;

						case 5:
						case 15:
						case 25:
						case 35:
							col = Color.orange;
							k=5;
							break;

						case 6:
						case 16:
						case 26:
						case 36:
							col = Color.green;
							k=6;
							break;

						case 7:
						case 17:
						case 27:
						case 37:
							col = Color.red;
							k=7;
							break;

						default:
							col = Color.black;
							k = 0;
							break;
					}
					mino[i][j] = new MinoPanel(k);
					MP.add(mino[i][j]);
				}
			}

			MP.revalidate();//再描画
			MP.repaint();

			phold.revalidate();//再描画
			phold.repaint();

			pnext1.revalidate();//再描画
			pnext1.repaint();

			pnext2.revalidate();//再描画
			pnext2.repaint();

			pnext3.revalidate();//再描画
			pnext3.repaint();

			try {								//待つ
            	Thread.sleep(50);
        	} catch (InterruptedException e) {
            	e.printStackTrace();
        	}

			n++;
			if(GM.matimasu()==1) n=0;
			if(n == 6){
				n = 0;
				switch(state){

					case 0:							//ミノ召喚
						GM.summon();
						state = 1;
						break;

					case 1:							//普通の自由落下
						t = GM.down();
						if(t == false){
							state = 2;
							game = GM.gamecheck();
							n=4;
						}
						break;

					case 2:							//そろったラインを消す
						GM.line();
						z = GM.z;
						state = 0;
						n=5;//回数-1;
						for(int i=0; i<22; i++){
							if(z[i] == 1){
								state = 3;
								n=2;
							}
						}
						if(state==3){
							switch(GM.linesum){
								case 1:
									scoreplus = 100;
									break;
								case 2:
									scoreplus = 300;
									break;
								case 3:
									scoreplus = 500;
									break;
								case 4:
									scoreplus = 1000;
									break;
								default:
									break;
							}
							renzoku++;
							score += scoreplus * renzoku;
							SCORE.setText(String.valueOf(score));
							if(renzoku >= 2){
								SCplus.setText("+"+String.valueOf(scoreplus * renzoku));
								SCpluslog.setText(String.valueOf(renzoku)+"consecutive bonus!");
							}
							else{
								SCplus.setText("+"+String.valueOf(scoreplus));
								SCpluslog.setText("");
							}
						}
						else{
							renzoku=0;
						}
						break;

					case 3:							//消えたライン分落とす
						GM.down2(z);
						state = 0;
						n=5;
						break;
					default:
						System.out.println("stop");
						state = 1;
						break;
				}
			}
			if(game){
				//Gameoverの表示

				JPanel PGameset = new JPanel();
				Color PGcol = new Color(0,0,0,120);
				PGameset.setBackground(PGcol);
				PGameset.setBounds(100,200,400,100);
				CP.add(PGameset);
				CP.setLayer(PGameset, 9);

				JLabel gameLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
				gameLabel.setForeground(Color.white);
				gameLabel.setFont(new Font("Arial", Font.PLAIN, 46));
				gameLabel.setBounds(0,0,600,500);
				CP.add(gameLabel);
				CP.setLayer(gameLabel, 10);

				break;
			}
		}
	}
}

