Feature: Crear y consultar un pedido en PetStore

  Background:
    Given el usuario está autenticado en la API de PetStore

  Scenario: Crear un nuevo pedido
    When el usuario crea un pedido con
      | id      | petId | quantity | shipDate | status  | complete |
      | 1000   | 1234  | 2        | 2023-11-22T12:00:00.000Z | placed  | false     |
    Then el código de estado de la respuesta debe ser 200
    And el cuerpo de la respuesta debe contener el pedido creado

  Scenario Outline: Consultar un pedido existente
    Given el usuario ha creado un pedido con el ID <orderId>
    When el usuario consulta el pedido con el ID <orderId>
    Then el código de estado de la respuesta debe ser 200
    And el cuerpo de la respuesta debe contener el pedido con el ID <orderId>

    Examples:
      | orderId |
      | 1000   |