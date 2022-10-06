package com.example.memberlist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.memberlist.ui.theme.MemberListTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemberListTheme {
                // A surface container using the 'background' color from the theme
                Screen()
            }
        }
    }
}

@Composable
fun Screen() {

    val viewModel: UserViewModel = viewModel()

    val users by viewModel.user.observeAsState()
    val userss = users!!

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)){

            Spacer(Modifier.height(10.dp))
            Head()
            LazyColumn(
                modifier = Modifier
                    .padding(15.dp, 10.dp)
            ){
                items(userss){
                    Log.d("User300", "${it.firstName}")
                    Item(it.firstName)
                }
            }
        }

    }
}


@Composable
fun Item(name : String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .layoutId("box")
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(50.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            CircleImage()
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ){
                Text(
                    text = name,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight(500)
                )
                Button(
                    onClick = {},
                    contentPadding = PaddingValues(horizontal = 40.dp, vertical = 0.dp),
                    modifier = Modifier
                        .border(1.dp, Color.Red, RectangleShape)
                        .height(30.dp),
                    colors = buttonColors(
                        backgroundColor = Color.Transparent
                    ),
                    elevation = elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        disabledElevation = 0.dp,
                        hoveredElevation = 0.dp,
                        focusedElevation = 0.dp
                    )
                ){
                    Text(
                        text = "Follow",
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun CircleImage(){
    Image(
        painter = painterResource(id = R.drawable.habesha_guy_11),
        contentDescription = null,
        modifier = Modifier
            .size(75.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Head(){
    Column() {
        SearchField("Search ...")
        RowSelection()
    }
}

@Composable
fun RowSelection(){

    Box(
        modifier= Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.CenterEnd
    ){
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .horizontalScroll(
                    rememberScrollState(),
                    true,
                    null
                )
        ){
            Text(
                text="All",
                color = Color.DarkGray,
            )
            Text(
                text="Groups",
                color = Color.DarkGray,
            )
            Text(
                text="People",
                color = Color.DarkGray,
            )
            Text(
                text="Photos",
                color = Color.DarkGray,
            )
            Text(
                text="Videos",
                color = Color.DarkGray,
            )
            Text(
                text="Pages",
                color = Color.DarkGray,
            )
            Text(
                text="Places",
                color = Color.DarkGray,
            )
            Text(
                text="Groups",
                color = Color.DarkGray,
            )
            Text(
                text="Events",
                color = Color.DarkGray,
            )
            Spacer(modifier = Modifier.width(20.dp))
        }
    }

}

@Composable
fun SearchField(
    hint: String
){
    val txt = rememberSaveable() {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 45.dp, max = 50.dp),
        contentAlignment = Alignment.Center
    ){
        BasicTextField(
            value = txt.value,
            onValueChange = {txt.value = it},
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .shadow(1.dp, RoundedCornerShape(30.dp), true),
            maxLines = 1,
            singleLine = true
        ){
            if(txt.value.isEmpty())
                Text(
                    text = hint,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .offset(20.dp, y = 15.dp)
                )

            /*
                SEARCH ICON

            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .offset(x = 100.dp)
            )

            */
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val userViewModel = UserViewModel()
    MemberListTheme {
    }
}