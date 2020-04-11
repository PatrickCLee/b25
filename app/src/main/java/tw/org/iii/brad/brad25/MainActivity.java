package tw.org.iii.brad.brad25;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;                      //2
    private DatabaseReference myRef,myRef2;                 //5
    private int i = 0;                                      //D
    private TextView mesg;                                  //I

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();              //3
        myRef = database.getReference("message");   //6 到遠端的資料庫看有沒有此reference,若無則創
        mesg = findViewById(R.id.mesg);                     //J

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {      //8 add可add多個,set只能set一個
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.v("brad", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.v("brad", "Failed to read value.", error.toException());
            }
        });
    }

    public void atest1(View view) {
        // Write a message to the database                              //1
//        FirebaseDatabase database = FirebaseDatabase.getInstance();   //4 註解掉
//        DatabaseReference myRef = database.getReference("message"); //key    7 註解掉
        myRef.setValue("Hello, World!");                                //value
    }

    public void atest2(View view) {                                     //9
        myRef2 = database.getReference("member"); //也是A       E

        myRef2.addValueEventListener(new ValueEventListener() { //H  增加一個Listener
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Member obj = dataSnapshot.getValue(Member.class);
//                Log.v("brad", "" + obj.getAge());
                mesg.setText("" + obj.getAge() + "\n");
                for(String name : obj.getNames()){
                    mesg.append(name + "\n" );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Member member = new Member();                                   //C
        member.setAge((int)(Math.random()*16));
        member.addName("Meowoo");       //改成剛才改的method              //G
        member.addName("Meowyy");
        member.addName("MeowMeow");
        myRef2.setValue(member);
    }
}
