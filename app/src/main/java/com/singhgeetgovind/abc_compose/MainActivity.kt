package com.singhgeetgovind.abc_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.F
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.singhgeetgovind.abc_compose.data.Item
import com.singhgeetgovind.abc_compose.data.Producer
import com.singhgeetgovind.abc_compose.ui.theme.ABCComposeTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ABCComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    HomeScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(@DrawableRes list:List<Int>,
             pagerState : PagerState,
             modifier: Modifier = Modifier ) {

    HorizontalPager(
        pageCount = list.size,
        state = pagerState,
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(horizontal = 10.dp, vertical = 15.dp)
        , verticalAlignment = Alignment.Top
    )
    { index->

        Image(
                painter = painterResource(id = list[index]),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(30.dp)))
    }

}

@Composable
fun LoadItem(
    list: List<Item>
) {
    LazyColumn(modifier = Modifier
        .wrapContentWidth()
        .padding(horizontal = 10.dp, vertical = 15.dp)){
        items(list.size, key = {
            list[it].unique
        },){
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                val item = list[it]
                Image(painter = painterResource(id = item.drawRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(15.dp)))
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = item.title, fontSize = TextUnit(18f, TextUnitType.Sp))
            }
        }
    }
}

@Composable
fun CircleIndicator(
    indicatorCount : Int,
    currentPosition : Int,
    color: Color=Color.Red,
    modifier: Modifier=Modifier
) {
    LazyRow(horizontalArrangement = Arrangement.Center, modifier=Modifier.fillMaxWidth()){
        items(indicatorCount){
            val color = if(currentPosition==it) Color(0xFF2196F3) else Color(0xFFB6B6B6)
            Box(
                modifier = modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(color)
                    .padding(horizontal = 5.dp)
            )
            Spacer(modifier = modifier.padding(5.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val snackbarHostState = remember{ SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )
            }, scrollBehavior = scrollBehavior,
                navigationIcon = {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,
                        modifier = Modifier.padding(horizontal = 15.dp))
                },
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) } ,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("FAB")
                }
            }) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
            }
        },
        modifier = Modifier.fillMaxSize(),
    ){

        val pagerState = rememberPagerState(0)
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
        ) {

                Carousel(listOfData, pagerState)
                CircleIndicator(
                    indicatorCount = listOfData.size,
                    currentPosition = pagerState.currentPage
                )
                with(if ((pagerState.currentPage % 2) == 0) Producer.item1 else Producer.item2) {
                    LoadItem(list = this)
                }

        }
    }
}

@Preview(uiMode = 0, showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ABCComposeTheme {
        HomeScreen()
    }
}
val  listOfData = listOf(R.drawable.dog1,R.drawable.dog2,R.drawable.dog3,R.drawable.dog4,R.drawable.dog5)