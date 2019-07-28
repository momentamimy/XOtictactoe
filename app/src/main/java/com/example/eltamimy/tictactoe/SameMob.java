package com.example.eltamimy.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thekhaeng.pushdownanim.PushDownAnim;

import de.hdodenhof.circleimageview.CircleImageView;
import me.rishabhkhanna.customtogglebutton.CustomToggleButton;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class SameMob extends AppCompatActivity {

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

    ImageView rest;


    CircleImageView secondPhoto, firstPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_same_mob);

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
        secondPhoto =findViewById(R.id.txt_client_icon);
        firstPhoto =findViewById(R.id.txt_host_icon);



        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PushDownAnim.setPushDownAnimTo( rest).setScale( MODE_SCALE , PushDownAnim.DEFAULT_PUSH_STATIC  )
                        .setOnClickListener( new View.OnClickListener(){
                            @Override
                            public void onClick( View view ){
                                Toast.makeText( SameMob.this, "restart", Toast.LENGTH_SHORT ).show();
                                rest(rest);
                            }
                        } );
            }
        });



    }

    public void B_1(View view) {
        if (squares[0]==2)
        {
            if (count%2==0) { clickX(b1,0); }
            else { clickO(b1,0); }
        }
    }

    public void B_2(View view) {
        if (squares[1]==2)
        {
            if (count%2==0) { clickX(b2,1); }
            else { clickO(b2,1); }
        }
    }

    public void B_3(View view) {
        if (squares[2]==2)
        {
            if (count%2==0) { clickX(b3,2); }
            else { clickO(b3,2); }
        }
    }

    public void B_4(View view) {
        if (squares[3]==2)
        {
            if (count%2==0) { clickX(b4,3); }
            else { clickO(b4,3); }
        }
    }

    public void B_5(View view) {
        if (squares[4]==2)
        {
            if (count%2==0) { clickX(b5,4); }
            else { clickO(b5,4); }
        }
    }

    public void B_6(View view) {
        if (squares[5]==2)
        {
            if (count%2==0) { clickX(b6,5); }
            else { clickO(b6,5); }
        }
    }

    public void B_7(View view) {
        if (squares[6]==2)
        {
            if (count%2==0) { clickX(b7,6); }
            else { clickO(b7,6); }
        }
    }

    public void B_8(View view) {
        if (squares[7]==2)
        {
            if (count%2==0) { clickX(b8,7); }
            else { clickO(b8,7); }
        }
    }

    public void B_9(View view) {
        if (squares[8]==2)
        if (count%2==0) { clickX(b9,8); }
        else { clickO(b9,8); }
    }



    public void clickX(ImageView b,int index)
    {
        count++;
        b.setImageResource(R.drawable.x);
        squares[index]=1;
        boolean win=X_Win();
        if(!win)
        {
            if (count==9)
            {
                textView.setText("draw...");
            }
        }
    }

    public void clickO(ImageView b,int index)
    {
        count++;
        b.setImageResource(R.drawable.o);
        squares[index]=0;
        boolean win=O_Win();
        if(!win)
        {
            if (count==9)
            {
                textView.setText("draw...");
            }
        }
    }


    public boolean X_Win()
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
            textView.setText("X win");
            return true;
        }
        return false;
    }

    public boolean O_Win()
    {
        if ((squares[0]==0&&squares[1]==0&&squares[2]==0)||(squares[3]==0&&squares[4]==0&&squares[5]==0)
                ||(squares[6]==0&&squares[7]==0&&squares[8]==0) ||(squares[0]==0&&squares[3]==0&&squares[6]==0)
                ||(squares[1]==0&&squares[4]==0&&squares[7]==0)||(squares[2]==0&&squares[5]==0&&squares[8]==0)
                ||(squares[0]==0&&squares[4]==0&&squares[8]==0)||(squares[2]==0&&squares[4]==0&&squares[6]==0))
        {
            for (int i=0;i<9;i++)
            {
                squares[i]=3;
            }
            textView.setText("O win");
            return true;
        }
        return false;
    }

    public void rest(View view) {
        count=0;
        for (int i=0;i<9;i++)
        {
            squares[i]=2;
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
        textView.setText("play...");

    }
}
