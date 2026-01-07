package com.example.myapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TitleLabel(
    title: String,
    modifier: Modifier = Modifier
        .height(40.dp).clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.primary)//,
        .fillMaxWidth(),
    color: Color = MaterialTheme.colorScheme.onPrimary,
    alignment: Alignment = Alignment.Center,
){
    Box(
        modifier = modifier,
        contentAlignment = alignment,
    ) {
        Text(
            text = title,
            color = color,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            //fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun KeyValueLabel(
    title: String,
    description: String,
    weightTitle: Float,
    weighDescription: Float
){
    Row(
        modifier = Modifier
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        TitleLabel(
            title = title,
            modifier= Modifier
                .weight(weightTitle)
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
        TitleLabel(
            description,
            modifier= Modifier
                .weight(weighDescription)
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 10.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            alignment = Alignment.CenterStart
        )
    }
}

@Composable
fun DoubleKeyValueLabel(
    firstTitle: String,
    secondTitle: String,
    firstDescription: String,
    secondDescription: String
){
    Row(
        modifier = Modifier
            .height(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        TitleLabel(
            title = firstTitle,
            modifier = Modifier
                .height(40.dp)
                .weight(1.0f)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
        TitleLabel(
            title = firstDescription,
            modifier= Modifier
                .weight(1.0f)
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 10.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            alignment = Alignment.CenterStart
        )
        Spacer(Modifier.size(8.dp))
        TitleLabel(
            title = secondTitle,
            modifier = Modifier
                .weight(1.0f)
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
        TitleLabel(
            title = secondDescription,
            modifier= Modifier
                .weight(1.5f)
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 10.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            alignment = Alignment.CenterStart
        )
    }
}

@Composable
fun BoxDescription(text: String){
    val isDarkMode = isSystemInDarkTheme()
    Box(
        modifier = Modifier
            .border(2.dp, MaterialTheme.colorScheme.primary)
            .padding(10.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            text = text,
            color = if(isDarkMode){MaterialTheme.colorScheme.onPrimary}else{MaterialTheme.colorScheme.primary},
            fontSize = MaterialTheme.typography.bodyLarge.fontSize
        )
    }
}
