package com.example.eltamimy.tictactoe;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.CompoundButton;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.thekhaeng.pushdownanim.PushDownAnim;

        import java.util.ArrayList;
        import java.util.Random;
        import java.util.Timer;
        import java.util.TimerTask;

        import de.hdodenhof.circleimageview.CircleImageView;
        import me.rishabhkhanna.customtogglebutton.CustomToggleButton;

        import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class SoloNormalActivity extends AppCompatActivity {

    TextView textView;

    ImageView b1;
    ImageView b2;
    ImageView b3;
    ImageView b4;
    ImageView b5;
    ImageView b6;
    ImageView b7;
    ImageView b8;
    ImageView b9;

    int count=0;

    int[] squares;

    boolean AI_Must_Win =false;

    ImageView rest;

    String playFristOrSecond="second";

    CustomToggleButton toggleButton;

    CircleImageView secondPhoto, firstPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_normal);

        squares=new int[10];

        for (int i=0;i<9;i++)
        {
            squares[i]=2;
        }
        textView=(TextView)findViewById(R.id.textv);
        b1=(ImageView) findViewById(R.id.b1);
        b2=(ImageView) findViewById(R.id.b2);
        b3=(ImageView) findViewById(R.id.b3);
        b4=(ImageView) findViewById(R.id.b4);
        b5=(ImageView) findViewById(R.id.b5);
        b6=(ImageView) findViewById(R.id.b6);
        b7=(ImageView) findViewById(R.id.b7);
        b8=(ImageView) findViewById(R.id.b8);
        b9=(ImageView) findViewById(R.id.b9);
        rest=findViewById(R.id.rest);
        toggleButton=findViewById(R.id.mytoggle);
        secondPhoto =findViewById(R.id.txt_client_icon);
        firstPhoto =findViewById(R.id.txt_host_icon);


        if (playFristOrSecond.equals("second")) {
            clickAcnimationAI(b5);
            squares[4]=0;
        }
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PushDownAnim.setPushDownAnimTo( rest).setScale( MODE_SCALE , PushDownAnim.DEFAULT_PUSH_STATIC  )
                        .setOnClickListener( new View.OnClickListener(){
                            @Override
                            public void onClick( View view ){
                                Toast.makeText( SoloNormalActivity.this, "restart", Toast.LENGTH_SHORT ).show();
                                rest(rest);
                            }
                        } );
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    playFristOrSecond="first";
                    rest(rest);
                    Toast.makeText(getApplicationContext(),"first",Toast.LENGTH_LONG).show();
                    secondPhoto.setImageResource(R.drawable.ic_a_i);
                    firstPhoto.setImageResource(R.drawable.ic_clientgame);
                }
                else
                {
                    playFristOrSecond="second";
                    rest(rest);
                    Toast.makeText(getApplicationContext(),"second",Toast.LENGTH_LONG).show();
                    secondPhoto.setImageResource(R.drawable.ic_clientgame);
                    firstPhoto.setImageResource(R.drawable.ic_a_i);
                }
            }
        });
    }

    public void clickAcnimation(ImageView v)
    {
        if (playFristOrSecond.equals("second")) {
            v.setImageResource(R.drawable.o);
        }
        else if (playFristOrSecond.equals("first")){
            v.setImageResource(R.drawable.x);
        }
        Animation fade_in= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        v.startAnimation(fade_in);
    }

    public void clickAcnimationAI(final ImageView v)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (playFristOrSecond.equals("second")) {
                            v.setImageResource(R.drawable.x);
                        }
                        else if (playFristOrSecond.equals("first")){
                            v.setImageResource(R.drawable.o);
                        }
                        Animation fade_in=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                        v.startAnimation(fade_in);
                    }
                });

            }}, 100);
    }

    public void rest(View view) {
        count=0;
        for (int i=0;i<9;i++)
        {
            squares[i]=2;
        }
        if (playFristOrSecond.equals("second")) {
            clickAcnimationAI(b5);
            squares[4]=0;
        }

        b1.setImageResource(0);
        b2.setImageResource(0);
        b3.setImageResource(0);
        b4.setImageResource(0);
        b5.setImageResource(0);
        b6.setImageResource(0);
        b7.setImageResource(0);
        b8.setImageResource(0);
        b9.setImageResource(0);
        textView.setText("");

    }


    public void B_1(View view) {


        if (squares[0]==2) {
            clickAcnimation(b1);
            count++;
            squares[0]=1;
            boolean win=User_Win();
            if (!win)
            {
                if (count==5)
                {
                    textView.setText("draw...");
                }
            }
            if (playFristOrSecond.equals("second")) {
                AI_X(1);
            }
            else if (playFristOrSecond.equals("first")){
                AI_O();
            }
        }


    }

    public void B_2(View view) {
        if (squares[1]==2) {
            clickAcnimation(b2);
            count++;
            squares[1]=1;
            boolean win=User_Win();
            if (!win)
            {
                if (count==5)
                {
                    textView.setText("draw...");
                }
            }
            if (count==1||count==2) {
                AI_Must_Win =true;
            }

            if (playFristOrSecond.equals("second")) {
                AI_X(2);
            }
            else if (playFristOrSecond.equals("first")){
                AI_O();
            }
        }
    }

    public void B_3(View view) {
        if (squares[2]==2) {
            clickAcnimation(b3);
            count++;
            squares[2]=1;
            boolean win=User_Win();
            if (!win)
            {
                if (count==5)
                {
                    textView.setText("draw...");
                }
            }
            if (playFristOrSecond.equals("second")) {
                AI_X(3);
            }
            else if (playFristOrSecond.equals("first")){
                AI_O();
            }
        }
    }

    public void B_4(View view) {
        if (squares[3]==2) {
            clickAcnimation(b4);
            count++;
            squares[3]=1;

            if (count==1||count==2) {
                AI_Must_Win =true;
            }
            boolean win=User_Win();
            if (!win)
            {
                if (count==5)
                {
                    textView.setText("draw...");
                }
            }
            if (playFristOrSecond.equals("second")) {
                AI_X(4);
            }
            else if (playFristOrSecond.equals("first")){
                AI_O();
            }
        }
    }

    public void B_5(View view) {
        if (squares[4]==2) {
            clickAcnimation(b5);
            count++;
            squares[4]=1;
            boolean win=User_Win();
            if (!win)
            {
                if (count==5)
                {
                    textView.setText("draw...");
                }
            }
            if (playFristOrSecond.equals("second")) {
                AI_X(5);
            }
            else if (playFristOrSecond.equals("first")){
                AI_O();
            }
        }
    }

    public void B_6(View view) {
        if (squares[5]==2) {
            clickAcnimation(b6);
            count++;
            squares[5]=1;
            boolean win=User_Win();
            if (!win)
            {
                if (count==5)
                {
                    textView.setText("draw...");
                }
            }
            if (count==1||count==2) {
                AI_Must_Win =true;
            }

            if (playFristOrSecond.equals("second")) {
                AI_X(6);
            }
            else if (playFristOrSecond.equals("first")){
                AI_O();
            }
        }
    }

    public void B_7(View view) {
        if (squares[6]==2) {
            clickAcnimation(b7);
            count++;
            squares[6]=1;
            boolean win=User_Win();
            if (!win)
            {
                if (count==5)
                {
                    textView.setText("draw...");
                }
            }
            if (playFristOrSecond.equals("second")) {
                AI_X(7);
            }
            else if (playFristOrSecond.equals("first")){
                AI_O();
            }
        }
    }

    public void B_8(View view) {
        if (squares[7]==2) {
            clickAcnimation(b8);
            count++;
            squares[7]=1;
            boolean win=User_Win();
            if (!win)
            {
                if (count==5)
                {
                    textView.setText("draw...");
                }
            }
            if (count==1||count==2) {
                AI_Must_Win =true;
            }
            if (playFristOrSecond.equals("second")) {
                AI_X(8);
            }
            else if (playFristOrSecond.equals("first")){
                AI_O();
            }
        }
    }

    public void B_9(View view) {
        if (squares[8]==2) {
            clickAcnimation(b9);
            count++;
            squares[8]=1;
            boolean win=User_Win();
            if (!win)
            {
                if (count==5)
                {
                    textView.setText("draw...");
                }
            }
            if (playFristOrSecond.equals("second")) {
                AI_X(9);
            }
            else if (playFristOrSecond.equals("first")){
                AI_O();
            }
        }
    }

    private void AI_O() {
        Log.d("count:::", String.valueOf(count));
        CanWin();
        boolean T=CheckThreat();
        if (T==false) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < squares.length; i++) {
                if (squares[i] == 2) {
                    list.add(i);
                }
            }
            if (list.size() != 0) {
                Random rand = new Random();
                int randomElement = list.get(rand.nextInt(list.size()));
                switch (randomElement) {
                    case 0:
                        clickAcnimationAI(b1);
                        squares[0] = 0;
                        break;
                    case 1:
                        clickAcnimationAI(b2);
                        squares[1] = 0;
                        break;
                    case 2:
                        clickAcnimationAI(b3);
                        squares[2] = 0;
                        break;
                    case 3:
                        clickAcnimationAI(b4);
                        squares[3] = 0;
                        break;
                    case 4:
                        clickAcnimationAI(b5);
                        squares[4] = 0;
                        break;
                    case 5:
                        clickAcnimationAI(b6);
                        squares[5] = 0;
                        break;
                    case 6:
                        clickAcnimationAI(b7);
                        squares[6] = 0;
                        break;
                    case 7:
                        clickAcnimationAI(b8);
                        squares[7] = 0;
                        break;
                    case 8:
                        clickAcnimationAI(b9);
                        squares[8] = 0;
                        break;
                }
            }
        }
    }
    private void AI_X(int index) {
        Log.d("count:::", String.valueOf(count));
        CanWin();
        boolean T=CheckThreat();
        if (T==true)
        {
            if (count==4)
            {
                textView.setText("draw...");
            }
        }
        if (T==false) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < squares.length; i++) {
                if (squares[i] == 2) {
                    list.add(i);
                }
            }
            if (list.size() != 0) {
                Random rand = new Random();
                int randomElement = list.get(rand.nextInt(list.size()));
                switch (randomElement) {
                    case 0:
                        clickAcnimationAI(b1);
                        squares[0] = 0;
                        if (count==4)
                        {
                            textView.setText("draw...");
                        }
                        break;
                    case 1:
                        clickAcnimationAI(b2);
                        squares[1] = 0;
                        if (count==4)
                        {
                            textView.setText("draw...");
                        }
                        break;
                    case 2:
                        clickAcnimationAI(b3);
                        squares[2] = 0;
                        if (count==4)
                        {
                            textView.setText("draw...");
                        }
                        break;
                    case 3:
                        clickAcnimationAI(b4);
                        squares[3] = 0;
                        if (count==4)
                        {
                            textView.setText("draw...");
                        }
                        break;
                    case 4:
                        clickAcnimationAI(b5);
                        squares[4] = 0;
                        if (count==4)
                        {
                            textView.setText("draw...");
                        }
                        break;
                    case 5:
                        clickAcnimationAI(b6);
                        squares[5] = 0;
                        if (count==4)
                        {
                            textView.setText("draw...");
                        }
                        break;
                    case 6:
                        clickAcnimationAI(b7);
                        squares[6] = 0;
                        if (count==4)
                        {
                            textView.setText("draw...");
                        }
                        break;
                    case 7:
                        clickAcnimationAI(b8);
                        squares[7] = 0;
                        if (count==4)
                        {
                            textView.setText("draw...");
                        }
                        break;
                    case 8:
                        clickAcnimationAI(b9);
                        squares[8] = 0;
                        if (count==4)
                        {
                            textView.setText("draw...");
                        }
                        break;
                }
            }
        }
    }

    public void AI_Win()
    {
        for (int i=0;i<9;i++)
        {
            squares[i]=3;
        }
        textView.setText("you lose");
    }

    public boolean User_Win()
    {
        if ((squares[0]==1&&squares[1]==1&&squares[2]==1)||(squares[3]==1&&squares[4]==1&&squares[5]==1)
                ||(squares[6]==1&&squares[7]==1&&squares[8]==1) ||(squares[0]==1&&squares[3]==1&&squares[6]==1)
                ||(squares[1]==1&&squares[4]==1&&squares[7]==1)||(squares[2]==1&&squares[5]==1&&squares[8]==1)
                ||(squares[0]==1&&squares[4]==1&&squares[8]==1)||(squares[2]==1&&squares[4]==1&&squares[6]==1))
        {
            for (int i=0;i<9;i++)
            {
                squares[i]=3;
            }
            textView.setText("you win");
            return true;
        }
        return false;
    }

    public void CanWin()
    {
        if (squares[0]==0&&squares[1]==0&&squares[2]==2)
        {
            clickAcnimationAI(b3);
            squares[2]=0;
            AI_Win();
        }else if (squares[0]==0&&squares[1]==2&&squares[2]==0)
        {
            clickAcnimationAI(b2);
            squares[1]=0;
            AI_Win();
        }else if (squares[0]==2&&squares[1]==0&&squares[2]==0)
        {
            clickAcnimationAI(b1);
            squares[0]=0;
            AI_Win();
        }


        if (squares[3]==0&&squares[4]==0&&squares[5]==2)
        {
            clickAcnimationAI(b6);
            squares[5]=0;
            AI_Win();
        }else if (squares[3]==0&&squares[4]==2&&squares[5]==0)
        {
            clickAcnimationAI(b5);
            squares[4]=0;
            AI_Win();
        }else if (squares[3]==2&&squares[4]==0&&squares[5]==0)
        {
            clickAcnimationAI(b4);
            squares[3]=0;
            AI_Win();
        }


        if (squares[6]==0&&squares[7]==0&&squares[8]==2)
        {
            clickAcnimationAI(b9);
            squares[8]=0;
            AI_Win();
        }else if (squares[6]==0&&squares[7]==2&&squares[8]==0)
        {
            clickAcnimationAI(b8);
            squares[7]=0;
            AI_Win();
        }else if (squares[6]==2&&squares[7]==0&&squares[8]==0)
        {
            clickAcnimationAI(b7);
            squares[6]=0;
            AI_Win();
        }









        if (squares[0]==0&&squares[3]==0&&squares[6]==2)
        {
            clickAcnimationAI(b7);
            squares[6]=0;
            AI_Win();
        }else if (squares[0]==0&&squares[3]==2&&squares[6]==0)
        {
            clickAcnimationAI(b4);
            squares[3]=0;
            AI_Win();
        }else if (squares[0]==2&&squares[3]==0&&squares[6]==0)
        {
            clickAcnimationAI(b1);
            squares[0]=0;
            AI_Win();
        }


        if (squares[1]==0&&squares[4]==0&&squares[7]==2)
        {
            clickAcnimationAI(b8);
            squares[7]=0;
            AI_Win();
        }else if (squares[1]==0&&squares[4]==2&&squares[7]==0)
        {
            clickAcnimationAI(b5);
            squares[4]=0;
            AI_Win();
        }else if (squares[1]==2&&squares[4]==0&&squares[7]==0)
        {
            clickAcnimationAI(b2);
            squares[1]=0;
            AI_Win();
        }


        if (squares[2]==0&&squares[5]==0&&squares[8]==2)
        {
            clickAcnimationAI(b9);
            squares[8]=0;
            AI_Win();
        }else if (squares[2]==0&&squares[5]==2&&squares[8]==0)
        {
            clickAcnimationAI(b6);
            squares[5]=0;
            AI_Win();
        }else if (squares[2]==2&&squares[5]==0&&squares[8]==0)
        {
            clickAcnimationAI(b3);
            squares[2]=0;
            AI_Win();
        }










        if (squares[0]==0&&squares[4]==0&&squares[8]==2)
        {
            clickAcnimationAI(b9);
            squares[8]=0;
            AI_Win();
        }else if (squares[0]==0&&squares[4]==2&&squares[8]==0)
        {
            clickAcnimationAI(b5);
            squares[4]=0;
            AI_Win();
        }else if (squares[0]==2&&squares[4]==0&&squares[8]==0)
        {
            clickAcnimationAI(b1);
            squares[0]=0;
            AI_Win();
        }


        if (squares[2]==0&&squares[4]==0&&squares[6]==2)
        {
            clickAcnimationAI(b7);
            squares[6]=0;
            AI_Win();
        }else if (squares[2]==0&&squares[4]==2&&squares[6]==0)
        {
            clickAcnimationAI(b5);
            squares[4]=0;
            AI_Win();
        }else if (squares[2]==2&&squares[4]==0&&squares[6]==0)
        {
            clickAcnimationAI(b3);
            squares[2]=0;
            AI_Win();
        }
    }

    public boolean CheckThreat()
    {
        if (squares[0]==1&&squares[1]==1&&squares[2]==2)
        {
            clickAcnimationAI(b3);
            squares[2]=0;
            return true;
        }else if (squares[0]==1&&squares[1]==2&&squares[2]==1)
        {
            clickAcnimationAI(b2);
            squares[1]=0;
            return true;
        }else if (squares[0]==2&&squares[1]==1&&squares[2]==1)
        {
            clickAcnimationAI(b1);
            squares[0]=0;
            return true;
        }


        if (squares[3]==1&&squares[4]==1&&squares[5]==2)
        {
            clickAcnimationAI(b6);
            squares[5]=0;
            return true;
        }else if (squares[3]==1&&squares[4]==2&&squares[5]==1)
        {
            clickAcnimationAI(b5);
            squares[4]=0;
            return true;
        }else if (squares[3]==2&&squares[4]==1&&squares[5]==1)
        {
            clickAcnimationAI(b4);
            squares[3]=0;
            return true;
        }


        if (squares[6]==1&&squares[7]==1&&squares[8]==2)
        {
            clickAcnimationAI(b9);
            squares[8]=0;
            return true;
        }else if (squares[6]==1&&squares[7]==2&&squares[8]==1)
        {
            clickAcnimationAI(b8);
            squares[7]=0;
            return true;
        }else if (squares[6]==2&&squares[7]==1&&squares[8]==1)
        {
            clickAcnimationAI(b7);
            squares[6]=0;
            return true;
        }









        if (squares[0]==1&&squares[3]==1&&squares[6]==2)
        {
            clickAcnimationAI(b7);
            squares[6]=0;
            return true;
        }else if (squares[0]==1&&squares[3]==2&&squares[6]==1)
        {
            clickAcnimationAI(b4);
            squares[3]=0;
            return true;
        }else if (squares[0]==2&&squares[3]==1&&squares[6]==1)
        {
            clickAcnimationAI(b1);
            squares[0]=0;
            return true;
        }


        if (squares[1]==1&&squares[4]==1&&squares[7]==2)
        {
            clickAcnimationAI(b8);
            squares[7]=0;
            return true;
        }else if (squares[1]==1&&squares[4]==2&&squares[7]==1)
        {
            clickAcnimationAI(b5);
            squares[4]=0;
            return true;
        }else if (squares[1]==2&&squares[4]==1&&squares[7]==1)
        {
            clickAcnimationAI(b2);
            squares[1]=0;
            return true;
        }


        if (squares[2]==1&&squares[5]==1&&squares[8]==2)
        {
            clickAcnimationAI(b9);
            squares[8]=0;
            return true;
        }else if (squares[2]==1&&squares[5]==2&&squares[8]==1)
        {
            clickAcnimationAI(b6);
            squares[5]=0;
            return true;
        }else if (squares[2]==2&&squares[5]==1&&squares[8]==1)
        {
            clickAcnimationAI(b3);
            squares[2]=0;
            return true;
        }










        if (squares[0]==1&&squares[4]==1&&squares[8]==2)
        {
            clickAcnimationAI(b9);
            squares[8]=0;
            return true;
        }else if (squares[0]==1&&squares[4]==2&&squares[8]==1)
        {
            clickAcnimationAI(b5);
            squares[4]=0;
            return true;
        }else if (squares[0]==2&&squares[4]==1&&squares[8]==1)
        {
            clickAcnimationAI(b1);
            squares[0]=0;
            return true;
        }


        if (squares[2]==1&&squares[4]==1&&squares[6]==2)
        {
            clickAcnimationAI(b7);
            squares[6]=0;
            return true;
        }else if (squares[2]==1&&squares[4]==2&&squares[6]==1)
        {
            clickAcnimationAI(b5);
            squares[4]=0;
            return true;
        }else if (squares[2]==2&&squares[4]==1&&squares[6]==1)
        {
            clickAcnimationAI(b3);
            squares[2]=0;
            return true;
        }
        return false;
    }
}
