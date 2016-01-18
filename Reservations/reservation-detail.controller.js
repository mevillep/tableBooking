/**
 * Created by Meville on 1/14/16.
 */
(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('ReservationDetailController', ReservationDetailController);

    ReservationDetailController.$inject = ['reservationdataService', '$routeParams'];

    function ReservationDetailController(reservationdataService, $routeParams) {
        var reservationDetailVm = this;

        reservationDetailVm.detail = null;

       reservationdataService
            .getReservationById($routeParams.id)
            .then(function(data){
                reservationDetailVm.detail = data;
            }, function (error){
                console.log(error);
            })

    }
})();