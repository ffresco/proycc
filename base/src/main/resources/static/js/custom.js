
    $(document).ready(function(){
      var date_input=$('input[name="txtFecha"]'); //our date input has the name "date"
      var container=$('.form-horizontal').length>0 ? $('.form-horizontal').parent() : "body";
      var options={
        format: 'dd/mm/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })
