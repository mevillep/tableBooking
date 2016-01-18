/**
 * Created by Meville on 1/14/16.
 */
(function(){
    'use strict';

    angular
        .module('myApp', ['ngRoute', 'angular-loading-bar','ngMessages'])
        .config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider'];
    function moduleConfig ($routeProvider) {
        $routeProvider
            .when('/customers', {
                templateUrl: '../Customer/customer-list.tmpl.html',
                controller: 'CustomerListController',
                controllerAs: 'customerListVm'
            })
            .when('/createCustomer', {
                templateUrl: '../Customer/customer-create.tmpl.html',
                controller: 'CustomerCreateController',
                controllerAs: 'customerCreateVm'
            })
            .when('/customers/:id', {
                templateUrl: '../Customer/customer-detail.tmpl.html',
                controller: 'CustomerDetailController',
                controllerAs: 'customerDetailVm'
            })
            .when('/deleteCustomer/:id', {
                templateUrl: '../Customer/customer-list.tmpl.html',
                controller: 'CustomerDeleteController',
                controllerAs: 'customerDeleteVm'
            })


            .when('/reservations', {
                templateUrl: '../Reservations/reservation-list.tmpl.html',
                controller: 'ReservationListController',
                controllerAs: 'reservationListVm'
            })
            .when('/reservations/:id', {
                templateUrl: '../Reservations/reservation-detail.tmpl.html',
                controller: 'ReservationDetailController',
                controllerAs: 'reservationDetailVm'
            })
            .when('/createReservation', {
                templateUrl: '../Reservations/reservation-create.tmpl.html',
                controller: 'ReservationCreateController',
                controllerAs: 'reservationCreateVm'
            })
            .when('/deleteReservation/:id', {
                templateUrl: '../Reservations/reservation-list.tmpl.html',
                controller: 'ReservationDeleteController',
                controllerAs: 'reservationDeleteVm'
            })


            .when('/tables', {
                templateUrl: '../Table/table-list.tmpl.html',
                controller: 'TableListController',
                controllerAs: 'tableListVm'
            })
            .when('/createTable', {
                templateUrl: '../Table/table-create.tmpl.html',
                controller: 'TableCreateController',
                controllerAs: 'tableCreateVm'
            })
            .when('/tables/:id', {
                templateUrl: '../Table/table-detail.tmpl.html',
                controller: 'TableDetailController',
                controllerAs: 'tableDetailVm'
            })
            .when('/deleteTable/:id', {
                templateUrl: '../Table/table-list.tmpl.html',
                controller: 'TableDeleteController',
                controllerAs: 'tableDeleteVm'
            })


            .when('/setting', {
                templateUrl: '../Restaurant/setting.tmpl.html',
                controller: 'SettingListController',
                controllerAs: 'settingListVm'
            })

            .otherwise({
                redirectTo: '/customers'
            })
    }
})();

