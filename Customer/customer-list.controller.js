/**
 * Created by Meville on 1/14/16.
 */


(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('CustomerListController', CustomerListController);

    CustomerListController.$inject = ['customerdataService'];

    function CustomerListController(customerdataService) {
        var customerListVm = this;

        customerListVm.customers = [];



        customerdataService.getCustomers()
            .then(function(data) {
            customerListVm.customers = data;
        }, function(error) {
            console.log(error);
        });

    }
})();