/**
 * Created by Meville on 1/16/16.
 */
/**
 * Created by Meville on 1/14/16.
 */

(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('ReservationCreateController', ReservationCreateController);

    ReservationCreateController.$inject = ['reservationdataService'];

    function ReservationCreateController(reservationdataService) {
        var reservationCreateVm = this;


        reservationCreateVm.register = function() {


            var reservation1 = angular.toJson(reservationCreateVm.reservation);

            console.log(reservation1);


            reservationdataService
                .createReservation(reservation1)
                .then(function(data){
                    console.log(data);
                }, function (error){
                    console.log(error);
                })


        };




    }



})();