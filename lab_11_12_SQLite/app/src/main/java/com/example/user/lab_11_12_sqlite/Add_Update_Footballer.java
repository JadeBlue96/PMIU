package com.example.user.lab_11_12_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.lab_11_12_sqlite.db.FootballerOperations;
import com.example.user.lab_11_12_sqlite.model.Footballer;

public class Add_Update_Footballer extends AppCompatActivity {
    private static final String EXTRA_EMP_ID = "footId";
    private static final String EXTRA_ADD_UPDATE = "foot_add_update";

    private RadioGroup radioGroup;
    private RadioButton maleRadioButton,femaleRadioButton;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText tNameEditText;
    private EditText carGoalsEditText;
    private Button addUpdateButton;
    private Footballer newFootballer;
    private Footballer oldFootballer;
    private String mode;
    private long footId;
    private FootballerOperations footballerData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_update_footballer);
        newFootballer = new Footballer();
        oldFootballer = new Footballer();
        firstNameEditText = (EditText)findViewById(R.id.edit_text_first_name);
        lastNameEditText = (EditText)findViewById(R.id.edit_text_last_name);
        radioGroup = (RadioGroup) findViewById(R.id.radio_gender);
        maleRadioButton = (RadioButton) findViewById(R.id.radio_male);
        femaleRadioButton = (RadioButton) findViewById(R.id.radio_female);
        tNameEditText = (EditText)findViewById(R.id.edit_text_team_name);
        carGoalsEditText=findViewById(R.id.edit_text_career_goals);
        addUpdateButton = (Button)findViewById(R.id.button_add_update_footballer);
        footballerData = new FootballerOperations(this);
        footballerData.open();
        radioGroup.clearCheck();
        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);
        if(mode.equals("Update")){

            addUpdateButton.setText("Update Footballer");
            footId = getIntent().getLongExtra(EXTRA_EMP_ID,0);

            initFootballer(footId);

        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radio_male)
                {
                    newFootballer.setGender("M");
                    if(mode.equals("Update")){
                        oldFootballer.setGender("M");
                    }
                }
                else if(checkedId==R.id.radio_female) {
                    newFootballer.setGender("F");
                    if(mode.equals("Update")){
                        oldFootballer.setGender("F");
                    }
                }
            }
        });

        addUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstNameEditText.getText().toString()=="" || lastNameEditText.getText().toString()=="" ||
                        carGoalsEditText.getText().toString()=="" || tNameEditText.getText().toString()==""
                        ||radioGroup.getCheckedRadioButtonId()==-1)
                {
                    Toast t = Toast.makeText(Add_Update_Footballer.this, "Incorrect data", Toast.LENGTH_SHORT);
                    t.show();
                    return;
                }
                if(mode.equals("Add")) {
                    newFootballer.setFirst_name(firstNameEditText.getText().toString());
                    newFootballer.setLast_name(lastNameEditText.getText().toString());
                    newFootballer.setCareer_goals(Integer.valueOf(carGoalsEditText.getText().toString()));
                    newFootballer.setTeam_name(tNameEditText.getText().toString());
                    footballerData.addFootballer(newFootballer);
                    Toast t = Toast.makeText(Add_Update_Footballer.this, "Footballer "+ newFootballer.getFirst_name() + "has been added successfully !", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(Add_Update_Footballer.this,MainActivity.class);
                    startActivity(i);
                }else {
                    oldFootballer.setFirst_name(firstNameEditText.getText().toString());
                    oldFootballer.setLast_name(lastNameEditText.getText().toString());
                    oldFootballer.setCareer_goals(Integer.valueOf(carGoalsEditText.getText().toString()));
                    oldFootballer.setTeam_name(tNameEditText.getText().toString());
                    footballerData.updateFootballer(oldFootballer);
                    Toast t = Toast.makeText(Add_Update_Footballer.this, "Footballer "+ oldFootballer.getFirst_name() + " has been updated successfully !", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(Add_Update_Footballer.this,MainActivity.class);
                    startActivity(i);

                }
            }
        });
    }

    private void initFootballer(long footId) {
        oldFootballer = footballerData.getFootballer(footId);
        firstNameEditText.setText(oldFootballer.getFirst_name());
        lastNameEditText.setText(oldFootballer.getLast_name());
        //radioGroup.check(oldFootballer.getGender().equals("M") ? R.id.radio_male : R.id.radio_female);
        tNameEditText.setText(oldFootballer.getTeam_name());
        //carGoalsEditText.setText(oldFootballer.getCareer_goals());
    }
}
