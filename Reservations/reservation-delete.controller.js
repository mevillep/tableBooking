
/**
 * Created by Meville on 1/14/16.
 */
(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('ReservationDeleteController', ReservationDeleteController);

    ReservationDeleteController.$inject = ['reservationdataService', '$routeParams'];

    function ReservationDeleteController(reservationdataService, $routeParams) {
        var reservationDeleteVm = this;

        reservationDeleteVm.detail = null;

        reservationdataService
            .deleteReservation($routeParams.id)
            .then(function(data){
                reservationDeleteVm.detail = data;
            }, function (error){
                console.log(error);
            })

    }
})();