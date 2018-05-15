
    $(document).ready(function(){
      var date_input=$('input[id="txtCotFechaDesde"]'); //our date input has the name "date"
      var container=$('.form-horizontal').length>0 ? $('.form-horizontal').parent() : "body";
      var options={
        dateFormat: 'dd/mm/yy',
        container: container,
        todayHighlight: true,
        autoclose: true,
      };
      date_input.datepicker(options);
    })

    $(document).ready(function(){
        $('[data-toggle="popover"]').popover(); 
    });
  