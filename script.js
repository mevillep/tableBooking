/**
 * Created by Meville on 12/22/15.
 */






    $( document ).ready(function() {

        $( ".ownerForm" ).hide();
        $( ".customerForm" ).hide();
        $( "#customerToggle" ).click(function() {

            $( ".customerForm" ).toggle( "slow" );
            $( ".ownerForm" ).hide();
        });

        $( "#ownerToggle" ).click(function() {
            $( ".ownerForm" ).toggle();
            $( ".customerForm" ).hide();
        });



//        var dateToday = new Date();
//       // console.log(dateToday);
//        $(function() {
//            $( "#date" ).datepicker({
//                numberOfMonths: 3,
//                showButtonPanel: true,
//                minDate: 0
//            });
//        });

        $("#date").datepicker({ minDate: 0 });




        $( ".generate" ).click(function() {

            $(".customerForm").validate();
//                $("#op").data("Your unique code is "+Math.floor(Math.random() * (1000)));

        });

        //setTimeout(function(){
        //    $( "#detail" ).html( "<small>Enter your booking details</small>" );
        //},5000)

        $( "#edit" ).bind( "click", function() {
            $( this ).alert( "Click to edit your reservation " );
        });



    });


