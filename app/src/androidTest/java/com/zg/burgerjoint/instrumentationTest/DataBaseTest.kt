package com.zg.burgerjoint.instrumentationTest

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.persistence.BurgerJointDatabase
import com.zg.burgerjoint.persistence.daos.BurgerDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4ClassRunner::class)
class DatabaseTest {
    // Dao and Database
    private lateinit var burgerDao: BurgerDao
    private lateinit var db: BurgerJointDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BurgerJointDatabase::class.java
        ).build()
        burgerDao = db.getBurgerDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertIntoDatabaseTest() {

        val burgerOne = BurgerVO()
        burgerOne.burgerName = "Chicken Burger"
        burgerOne.burgerDescription = "The hot chicken sandwich or simply \"hot chicken\" (Quebec French: sandwich chaud au poulet) is a type of chicken sandwich consisting of chicken, sliced bread, and gravy (which is generally poutine sauce). The sandwich is usually served with green peas and commonly found in Eastern Canadian cuisine. It's especially popular in Quebec and is often considered one of the province's staple dishes.[7][8] Since it is so commonly found in eateries of Quebec (Rôtisserie St-Hubert, Valentine, e.g.) and less seen outside the province, many Québécois regard it as a part of Quebec cuisine and believe it to have originated in the province.[7] This combination of chicken, gravy, and peas is known by its own term: galvaude,[7] seen in poutine galvaude.\n" +
                "\n" +
                "Although less featured in other areas of North America, the sandwich is also found in small diners from the Canadian Maritimes[9] and throughout the Southeastern United States.[10]\n" +
                "\n" +
                "The sandwich was a working-class dish already common and well established in North American cuisine by the early 1900s[11] and featured on the food menus of pharmacists and druggists of the time.[12] Due to its ease of preparation and its minimal costs, the sandwich was also widely served in the mess halls and cafeterias of the mid-1900s.[13][14]\n" +
                "\n" +
                "This style of sandwich often makes use of leftovers from a previous meal. Substituting turkey for the chicken would make a hot turkey sandwich[15] and substituting roast beef makes a variety of the roast beef sandwich.[16]"
        burgerOne.burgerImageUrl = "https://vignette.wikia.nocookie.net/simpsons-restaurants/images/2/20/Spicy_Clucker.png/revision/latest?cb=20131125185837"

        val burgerTwo = BurgerVO()
        burgerTwo.burgerName = "Big Mac Burger"
        burgerTwo.burgerDescription = "The Two all-beef patties, special sauce, lettuce, cheese, pickles, onions – on a sesame seed bun concept for the jingle was created by Keith Reinhard, Chairman Emeritus of DDB Worldwide, and his creative group at Needham Harper and Steers. Originally, the ingredients appeared as a one-word heading for a McDonald's ad developed for college newspapers. The words were then set to music created by Mark Vieha, who performed the original jingle. The first run of commercials ran only a year and a half, going off the air in 1976, but its popularity remained beyond its TV life. Subsequent to the jingle, McDonald's followed up with a promotion based on its customers spontaneously having a \"Big Mac Attack\".\n" +
                "\n" +
                "In the United States during the original campaign many franchises ran promotions that awarded a free burger to customers who could recite the slogan within a specified time (usually two or three seconds). One example of its success was that the McDonald's operators in New York City ran out of Big Mac buns.[citation needed] McDonald's Australia emulated this promotion in the mid-1980s, and some Brazilian McDonald's around the same time (only offering a free glass of Coca-Cola instead), in the Portuguese-language version, which is \"Dois hambúrgueres, alface, queijo, molho especial, cebola e picles num pão com gergelim\".\n" +
                "\n" +
                "In 2003, McDonald's revived the phrase. In an English-language ad from McDonald's international \"i'm lovin' it\" campaign, a rapper rapidly spouts off the trademark in the background music. Also in 2003, American Greetings and Carlton Cards released a Christmas ornament of a Big Mac, on which the slogan was both printed and played aloud by pulling on a string. Roy Bergold, National Advertising Manager at McDonald's, has a big hand in championing the original campaign and helping to bring it back.\n" +
                "\n" +
                "In 2008 McDonald's Malaysia revived the phrase. The revival includes the original prize of a free Big Mac if the customer is able to recite the phrase in under four seconds. It was released in May, along with the promotional Mega Mac, which has four beef patties instead of two.[13]"
        burgerTwo.burgerImageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Big_Mac_hamburger.jpg/470px-Big_Mac_hamburger.jpg"

        var  list :List<BurgerVO> = Arrays.asList(burgerOne,burgerTwo)
        burgerDao.insertBurgers(list)
        assert(burgerDao.findBurgerById(burgerOne.burgerId).value?.burgerId == burgerOne.burgerId)
    }


}
