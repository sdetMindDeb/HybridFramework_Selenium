package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {

        String path=".\\testData\\ProfileTestData.xlsx";

        ExcelUtility xlutil=new ExcelUtility(path);

        int totalRow=xlutil.getRowCount("Sheet1");
        int totalcol=xlutil.getCellCount("Sheet1",1);

        String loginData[][]=new String[totalRow][totalcol];

        for(int i=1;i<=totalRow;i++){
            for(int j=0;j<totalcol;j++){
                loginData[i-1][j]=xlutil.getCellData("Sheet1",i,j);
            }
        }

        return loginData;
    }
}