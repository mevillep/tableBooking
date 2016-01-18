/**
 * Created by Meville on 1/14/16.
 */
(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('ReservationListController', ReservationListController);

    ReservationListController.$inject = ['reservationdataService'];

    function ReservationListController(reservationdataService) {
        var reservationListVm = this;

        reservationListVm.reservations = [];

        reservationdataService
            .getReservations()
            .then(function(data) {
                reservationListVm.reservations = data;
            }, function(error) {
                console.log(error);
            });

    }
})();