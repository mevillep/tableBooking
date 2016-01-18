/**
 * Created by Meville on 1/14/16.
 */

(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('CustomerCreateController', CustomerCreateController);

    CustomerCreateController.$inject = ['customerdataService'];

    function CustomerCreateController(customerdataService) {
        var customerCreateVm = this;


        customerCreateVm.register = function() {


            var user1 =angular.toJson(customerCreateVm.user);

            console.log(user1);


            customerdataService
                .createCustomer(user1)
                .then(function(data){
                    customerCreateVm.code = data;
                }, function (error){
                    console.log(error);
                })


        };

        customerCreateVm.autoRegister = function() {


            var user1 =angular.toJson(customerCreateVm.user);

            console.log(user1);


            customerdataService
                .autoCreateCustomer(user1)
                .then(function(data){
                    customerCreateVm.code = data;
                }, function (error){
                    console.log(error);
                })


        };




    }



})();