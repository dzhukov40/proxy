import {getBackEnviroment} from './backEnviroment'



var backEnviroment = getBackEnviroment();



export function testPipeGet() {
    backEnviroment.get("тестик");
}


