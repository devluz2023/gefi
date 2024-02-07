const Router = require('express');
const homeController = require('../controllers/home');
const router = Router()

const rota = `/`;
router.get(rota,homeController.home);
router.get('/equipamento',homeController.equipamento);
router.get('/controle',homeController.controle);
router.get('/usuario',homeController.usuario);
router.get('/pendencia',homeController.pendencia);
router.get('/area',homeController.area);
router.get('/solicitacao',homeController.solicitacao);
router.get('*',homeController.solicitacao);

module.exports = router;


