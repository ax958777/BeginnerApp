package com.example.beginnerapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.beginnerapp.model.Product

@Composable
fun ProductCard(
    product: Product,
    onAddToCart: (Product)->Unit,
    modifier:Modifier=Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape= RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier=Modifier.padding(16.dp)
        ){
            //Product Name
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier=Modifier.height(16.dp))

            //Product Image
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription=product.name,
                modifier=Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
                contentScale= ContentScale.Crop
            )

            Spacer(modifier=Modifier.height(16.dp))

            // Price and Rating Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleMedium,
                    color=MaterialTheme.colorScheme.primary
                )

                RatingBar(rating=product.rating)

            }

            Spacer(modifier=Modifier.height(16.dp))

            //Description
            Text(
                text = product.description,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier=Modifier.height(16.dp))

            // Stock Status and Add To Cart Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = if (product.isInStock) "In Stock" else "Out of Stock",
                    color= if (product.isInStock) MaterialTheme.colorScheme.primary
                           else
                        MaterialTheme.colorScheme.error
                )

                Button(
                   onClick = { onAddToCart(product)} ,
                    enabled=product.isInStock
                ){
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Add to Cart"
                    )
                    Spacer(modifier=Modifier.width(8.dp))
                    Text("Add to Cart")
                }
            }

        }
    }
}