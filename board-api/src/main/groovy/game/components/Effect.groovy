package game.components

import game.entitys.Entity

/**
 * Created by tejp on 17/07/15.
 */
interface Effect extends Component{
    void perform (Entity entity)
}