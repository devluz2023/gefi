const Router = require('express');
const controleController = require('../controllers/controle');
const router = Router()

const rota = `/controles`;
router.post(rota, controleController.adiciona);
router.patch(`${rota}/:id`, controleController.atualiza);
router.get(`${rota}/coletivo/:coletivo/role/:role/usuario/:usuario_id/area/:area_id`, controleController.lista);
router.get(`${rota}/:id`, controleController.buscaPorId);
router.get(`${rota}/area/:area_id`, controleController.listaPorArea);
router.get(`${rota}/usuario/:usuario_id`, controleController.listaPorUsuario);
router.delete(`${rota}/:id`, controleController.deleta);


module.exports = router;


