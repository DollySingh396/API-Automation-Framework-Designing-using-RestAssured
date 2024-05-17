package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class dataProviders {


    @DataProvider(name = "UserData")
    public String[][] getAllData() throws IOException{


        // accessing the file from path
        String path = System.getProperty("user.dir")+"//testData/testDataForDataDrivenTesting.xlsx";
        // created an object of XLUtility class
        XLUtility xl = new XLUtility(path);

        // calling xlUtility class methods
        int rowNum = xl.getRowCount("Sheet1");
        int colCount = xl.getCellCount("Sheet1", 1);

        // creating a 2D array of size same as data in excel
        String apiData[][] = new String[rowNum][colCount];


        //reading from row 1 & col 0
        for(int i = 1; i <= rowNum; i++){
            for(int j = 0; j < colCount; j++){
                apiData[i-1][j] = xl.getCellData("Sheet1", i, j);
            }
        }

        return apiData;

    }


    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {

        String path = System.getProperty("user.dir")+"//testData/testDataForDataDrivenTesting.xlsx";
        XLUtility xl = new XLUtility(path);

        int rowNum = xl.getRowCount("Sheet1");

        String apiData[] = new String[rowNum];

        for(int i = 1; i <= rowNum; i++){

            apiData[i-1] = xl.getCellData("Sheet1", i, 1);
        }
        return apiData;
    }
}
