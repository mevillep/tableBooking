
/**
 * Created by Meville on 1/14/16.
 */
(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('CustomerDeleteController', CustomerDeleteController);

    CustomerDeleteController.$inject = ['customerdataService', '$routeParams'];

    function CustomerDeleteController(customerdataService, $routeParams) {
        var customerDeleteVm = this;

        customerDeleteVm.detail = null;

        customerdataService
            .deleteCustomer($routeParams.id)
            .then(function(data){
                customerDeleteVm.detail = data;
            }, function (error){
                console.log(error);
            })

    }
})();