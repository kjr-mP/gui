//FacesAWTMain.java
//FacesAWTMain 目標 インナークラスのFaceObjクラスを作ってみよう。描画処理を移譲してください。
//3x3　の顔を描画してください。色などもぬってオリジナルな楽しい顔にしてください。

package guibasic;


import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FacesAWTMain {

	public static void main(String[] args){
		new FacesAWTMain();
	}

    FacesAWTMain(){
		FaceFrame f = new FaceFrame();
		f.setSize(800,800);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
			System.exit(0);}});
		f.setVisible(true);
	}
	// インナークラスを定義
	class FaceFrame extends Frame{
        private int xStart0=50;
	    private int yStart0=50;
	
	    FaceFrame(){
	    	FaceObj fobj= new FaceObj();
        }

    	public void paint(Graphics g) {
    	// この中には、g.drawLine というのは入ってこない
    	// Graphicsクラス(型のようなもの---今は--)のgという変数はメソッドに渡す
        FaceObj[] fobjs= new FaceObj[9];

        for(int j=0;j<3;j++) {
            for (int i = 0; i < 3; i++) {
                fobjs[i+3*j]= new FaceObj();
                fobjs[i+3*j].setPosition(200*i+50,200*j+50);
                fobjs[i+3*j].setEmotionLevel(i,j);
                fobjs[i+3*j].drawFace(g);
            }
        }
	    }
    }//FaceFrame end

	//Faceクラスを作ってみよう。
	private class FaceObj{
	    private int w=200;
	    private int h=200;
	    private int xStart=300;
	    private int yStart=300;
	    private int i=0;
	    private int j=0;

        public void drawFace(Graphics g) {
            drawRim(g);
	    	drawBrow(g, 10); 
	    	drawEye(g, 25);
	    	drawNose(g, 40);
	    	drawMouth(g, 100);
        }

        public void setPosition(int xStart0, int yStart0) {
			this.xStart=xStart0;
			this.yStart=yStart0;
		}

        public void setEmotionLevel(int i,int j){
            this.i=i;
            this.j=j;
        }

        public void drawRim(Graphics g) {  // wが横幅、hが縦幅
			g.setColor(Color.GREEN);
			g.fillRoundRect(xStart, yStart, w, h, 100, 100);
			g.setColor(Color.black);
			g.drawRoundRect(xStart, yStart, w, h, 100, 100);
        }

	    public void drawBrow(Graphics g,int updown) { // xは目の幅 呼ばれる方(=定義する方)
		int wx1 = xStart + w*2/8;
		int wx2 = xStart + w*5/8;
		int wy = yStart + h*2/5;
		g.drawLine(wx1, wy+updown*i, wx1+w*1/8, wy);
		g.drawLine(wx2, wy, wx2+w*1/8, wy+updown*j);
	}

	    public void drawNose(Graphics g, int nx) { // xは鼻の長さ
	    }

	    public void drawEye(Graphics g, int r) { // rは目の半径
			g.setColor(Color.white);
			g.fillRoundRect(xStart+45-10,yStart+100-10,r+20,r+20,r+20,r+20);
			g.fillRoundRect(xStart+w-r-45-10,yStart+100-10,r+20,r+20,r+20,r+20);
			g.setColor(Color.black);
            g.drawOval(xStart+45-10,yStart+100-10,r+20,r+20);
            g.drawOval(xStart+w-r-45-10,yStart+100-10,r+20,r+20);
			g.fillRoundRect(xStart+45,yStart+100,r,r,r,r);
			g.fillRoundRect(xStart+w-r-45,yStart+100,r,r,r,r);
    	}

	    public void drawMouth(Graphics g, int mx) { // xは口の幅
	    	int xMiddle = xStart + w/2;
	    	int yMiddle = yStart + h - 30;
	    	g.drawLine(xMiddle - mx/2, yMiddle+10*i, xMiddle, yMiddle);
	    	g.drawLine(xMiddle, yMiddle, xMiddle + mx/2, yMiddle+10*j);
	    }
	}

}//Faces class end



