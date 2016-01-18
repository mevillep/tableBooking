/**
 * Created by Meville on 1/14/16.
 */
(function() {
    'use strict';

    angular
        .module('myApp')
        .controller('CustomerDetailController', CustomerDetailController);

    CustomerDetailController.$inject = ['customerdataService', '$routeParams'];

    function CustomerDetailController(customerdataService, $routeParams) {
        var customerDetailVm = this;

        customerDetailVm.detail = null;

        customerdataService
            .getCustomerById($routeParams.id)
            .then(function (data) {
                customerDetailVm.detail = data;
            }, function (error) {
                console.log(error);
            })
        console.log(customerDetailVm);


        customerDetailVm.update = function ()
        {


            var user1 = angular.toJson(customerDetailVm.detail);

            console.log(user1);


            customerdataService
                .updateCustomer($routeParams.id,user1)
                .then(function (data) {
                    customerCreateVm.code = data;
                }, function (error) {
                    console.log(error);
                })


        }


    }


    })();