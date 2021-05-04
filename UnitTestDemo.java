package April_26;


import org.junit.Assert;
import org.junit.Test;


public class UnitTestDemo {

        @Test
        public void test1(){

            Assert.assertEquals( "avaj", new StringUtils().reverse("java"));
        }


        @Test
        public void test2(){

            Assert.assertEquals( "olleH", new StringUtils().reverse("Hello"));
        }
    }

