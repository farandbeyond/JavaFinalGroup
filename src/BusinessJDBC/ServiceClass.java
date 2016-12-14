/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessJDBC;

/**
 *
 * @author Lee_G
 */
public class ServiceClass 
{
    if(selectEmpType.getSelectedItem() == "Hourly")
    {
        Employee temp = new HourlyEmployee(txtFirstName.getText(), txtLastName.getText(),txtGender.getText(), txtContactNum.getText(), 
                txtAddress.getText(), parseInt(txtEmpNum.getText()), parseInt(txtEmpSIN.getText()), parseInt(txtYear.getText()), parseInt(txtMonth.getText()),
        parseInt(txtDay.getText()), txtEmpTitle.getText(), parseDouble(txtPayRate.getText()), parseDouble(txtHoursWorked.getText()));
    }
    if(selectedEmpType.getSelectedItem() == "Salary")
    {
        Employee temp = new HourlyEmployee(txtFirstName.getText(), txtLastName.getText(),txtGender.getText(), txtContactNum.getText(), 
        txtAddress.getText(), parseInt(txtEmpNum.getText()), parseInt(txtEmpSIN.getText()), parseInt(txtYear.getText()), parseInt(txtMonth.getText()),
        parseInt(txtDay.getText()), txtEmpTitle.getText(), parseDouble(txtSalary.getText()));
    }
    if(selectedEmpType.getSelectedItem() == "Commission")
    {
        Employee temp = new HourlyEmployee(txtFirstName.getText(), txtLastName.getText(),txtGender.getText(), txtContactNum.getText(), 
        txtAddress.getText(), parseInt(txtEmpNum.getText()), parseInt(txtEmpSIN.getText()), parseInt(txtYear.getText()), parseInt(txtMonth.getText()),
        parseInt(txtDay.getText()), txtEmpTitle.getText(), parseDouble(txtTotalSales.getText()), parseDouble(txtCommissionRate.getText()));
    }
        Product temp = new Product(txtProductName.getText(), selectProductManufacturer.getSelectedItem().getName(), parseDouble(txtProductPrice.getText()),
            parseInt(txtProductStock.getText()), txtProductType.getText());
                 
        Manufacturer temp = new Manufacturer(txtManufacturerName.getText(), txtManufacturerContactNum.getText(), txtManufacturerAddress.getText());
        
        Customer temp = new Customer(txtCustomerFirstName.getText(), txtCustomerLastName.getText(), txtBillingAddress.getText(), txtPhoneNumber.getText(),
                                parseInt(txtCustomerID.getText()));
        
        Sale temp = new Sale (parseInt(txtSalesCustomerID.getText()), parseInt(txtEmpNo.getText()), parseInt(txtProdID.getText()));

}
