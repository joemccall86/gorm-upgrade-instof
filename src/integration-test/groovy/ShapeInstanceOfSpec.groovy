import gorm.upgrade.instof.Circle
import gorm.upgrade.instof.Drawing
import gorm.upgrade.instof.Point
import gorm.upgrade.instof.Shape
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification
import spock.lang.Unroll

@Integration
@Rollback
class ShapeInstanceOfSpec extends Specification {

    @Unroll
    def 'instanceof information is maintained when queryDrawing is #queryDrawing'() {
        given: 'A drawing with a shape'
        Drawing.withNewSession {
            new Drawing(
                    drawnBy: 'John Doe',
                    shape: new Circle(
                            center: new Point(xCoord: 30.0, yCoord: -90.0),
                            radius: 30.0,
                            label: 'New Orleans Zone',
                            alpha: 0.1f
                    )
            ).save(failOnError: true)
        }

        when: 'Drawings are queried in this session'
        queryDrawing? Drawing.all: null

        then: 'the shape has its type information'
        Shape.first() instanceof Circle

        where:
        queryDrawing << [true, false]
    }

}

