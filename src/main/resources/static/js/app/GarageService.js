'use strict';

angular.module('crudApp').factory('GarageService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllGarages: loadAllGarages,
                getAllGarages: getAllGarages,
                getGarage: getGarage,
                createGarage: createGarage,
                updateGarage: updateGarage,
                removeGarage: removeGarage
            };

            return factory;

            function loadAllGarages() {
                console.log('Recherche de tous les garages');
                var deferred = $q.defer();
                $http.get(urls.GARAGE_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Recherche des garages réussie');
                            $localStorage.garages = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Erreur lors de la recherche des garages');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllGarages(){
                return $localStorage.garages;
            }

            function getGarage(id) {
                console.log('Recherche du garage avec l''id :'+id);
                var deferred = $q.defer();
                $http.get(urls.GARAGE_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Succès de la recherche du Garage avec l''id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erreur lors de la recherche du garage avec l''id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createGarage(garage) {
                console.log('Creation d''un Garage');
                var deferred = $q.defer();
                $http.post(urls.GARAGE_SERVICE_API, garage)
                    .then(
                        function (response) {
                            loadAllGarages();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Erreur lors de la creation d''un Garage : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateGarage(garage, id) {
                console.log('Mise à jour du Garage avec l''id '+id);
                var deferred = $q.defer();
                $http.put(urls.GARAGE_SERVICE_API + id, garage)
                    .then(
                        function (response) {
                            loadAllGarages();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erreur lors de la mise à jour du Garage avec l''id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeGarage(id) {
                console.log('Suppression du Garage avec l''id '+id);
                var deferred = $q.defer();
                $http.delete(urls.GARAGE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllGarages();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Erreur lors de la suppression du Garage avec l'id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);