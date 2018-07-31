package gorm.upgrade.instof

class BootStrap {

    def init = { servletContext ->

        // Create some shapes in a transaction
        Shape.withNewTransaction {
            new Circle(
                    center: new Point(xCoord: 30.0, yCoord: -90.0),
                    radius: 30.0,
                    label: 'New Orleans Zone',
                    alpha: 0.1f
            ).save(failOnError: true)

            new Rectangle(
                    topLeft: new Point(xCoord: 21.9, yCoord: -81.239),
                    bottomRight: new Point(xCoord: 22.29, yCoord: -82.394),
                    label: 'Test rectangle',
                    alpha: 0.2f
            ).save(failOnError: true)
        }

        // Grab the shapes and print their types
        Shape.withNewTransaction {
            log.debug("Shape.all:")
            Shape.all.each this.&printShapeInfo
        }

        Circle.withNewTransaction {
            log.debug "Circle.all:"
            Circle.all.each this.&printShapeInfo
        }

        Circle.withNewTransaction {
            log.debug "Rectangle.all:"
            Rectangle.all.each this.&printShapeInfo
        }

    }

    void printShapeInfo(Shape shape) {
        log.debug "shape = ${shape}"
        log.debug "shape class = ${shape.class.simpleName}"
        log.debug "shape instanceof Circle = ${shape instanceof Circle}"
        log.debug "shape instanceof Rectangle = ${shape instanceof Rectangle}"
    }

    def destroy = {
    }
}
