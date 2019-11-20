class Map{
    constructor(element) {
        this.element = element;
        this.destinationCities = [];
        this.originCities = [];
        this.currentOrigin = null;
    }

    update(originCities, destinationCities) {
        this.originImageSeries.data = originCities;
        this.destinationImageSeries.data = destinationCities;

        // clear old
        this.lineSeries.mapLines.clear();
        this.lineSeries.toBack();
        this.worldPolygonSeries.toBack();
    }

    updateLines(){
        this.showLines(this.originImageSeries.dataItems.getIndex(0));
    }

    renderOriginCities() {
        this.originImageTemplate.propertyFields.latitude = "latitude";
        this.originImageTemplate.propertyFields.longitude = "longitude";
        this.originImageTemplate.propertyFields.id = "id";

        this.originImageTemplate.cursorOverStyle = am4core.MouseCursorStyle.pointer;
        this.originImageTemplate.nonScaling = true;
        this.originImageTemplate.tooltipText = "{title}";

        this.originImageTemplate.setStateOnChildren = true;
        this.originImageTemplate.states.create("hover");

        this.originImageTemplate.horizontalCenter = "middle";
        this.originImageTemplate.verticalCenter = "middle";

        let originHitCircle = this.originImageTemplate.createChild(am4core.Circle);
        originHitCircle.radius = 11;
        originHitCircle.fill = this.interfaceColors.getFor("background");

        let originTargetIcon = this.originImageTemplate.createChild(am4core.Sprite);
        originTargetIcon.fill = this.interfaceColors.getFor("alternativeBackground");
        originTargetIcon.strokeWidth = 0;
        originTargetIcon.scale = 1.3;
        originTargetIcon.horizontalCenter = "middle";
        originTargetIcon.verticalCenter = "middle";
        originTargetIcon.path = this.targetSVG;

        let originHoverState = originTargetIcon.states.create("hover");
        originHoverState.properties.fill = this.chart.colors.getIndex(1);
        // when hit on city, change lines

        let self = this;
        this.originImageTemplate.events.on("hit", function(event) {
            self.showLines(event.target.dataItem);
        });

        this.originImageSeries.data = this.originCities;

        this.chart.events.on("ready", function() {
            self.showLines(self.originImageSeries.dataItems.getIndex(0));
        });
    }

    renderDestinationCities() {
        this.destinationImageTemplate.nonScaling = true;
        this.destinationImageTemplate.tooltipText = "{title}";
        this.destinationImageTemplate.fill = this.interfaceColors.getFor("alternativeBackground");
        this.destinationImageTemplate.setStateOnChildren = true;
        this.destinationImageTemplate.states.create("hover");

        this.destinationImageTemplate.propertyFields.latitude = "latitude";
        this.destinationImageTemplate.propertyFields.longitude = "longitude";
        this.destinationImageTemplate.propertyFields.id = "id";

        let destinationHitCircle = this.destinationImageTemplate.createChild(am4core.Circle);
        destinationHitCircle.radius = 7;
        destinationHitCircle.fillOpacity = 1;
        destinationHitCircle.fill = this.interfaceColors.getFor("background");

        let destinationTargetIcon = this.destinationImageTemplate.createChild(am4core.Sprite);
        destinationTargetIcon.scale = 0.7;
        destinationTargetIcon.path = this.targetSVG;
        destinationTargetIcon.horizontalCenter = "middle";
        destinationTargetIcon.verticalCenter = "middle";

        this.destinationImageSeries.data = this.destinationCities;
    }

    showLines(origin) {
        // clear old
        this.lineSeries.mapLines.clear();
        this.lineSeries.toBack();
        this.worldPolygonSeries.toBack();

        if (origin !== undefined) {
            let dataContext = origin.dataContext;
            let destinations = dataContext.destinations;


            this.currentOrigin = origin;

            if (destinations) {
                for (let i = 0; i < destinations.length; i++) {
                    let line = this.lineSeries.mapLines.create();
                    line.imagesToConnect = [origin.mapImage.id, destinations[i]];
                }
            }

            this.title.text = "Routes from " + dataContext.title;

            this.chart.zoomToGeoPoint({ latitude: dataContext.zoomLatitude, longitude: dataContext.zoomLongitude }, dataContext.zoomLevel, true);
        }
    }

    render() {
        let self = this;

        am4core.ready(function() {

            // Themes begin
            am4core.useTheme(am4themes_animated);
            // Themes end

            // Create map instance
            self.chart = am4core.create(self.element, am4maps.MapChart);
            self.interfaceColors = new am4core.InterfaceColorSet();

            // Set map definition
            self.chart.geodata = am4geodata_worldLow;

            // Set projection
            self.chart.projection = new am4maps.projections.Mercator();

            self.targetSVG = "M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z";

            // Default to Nur-Sultan view
            self.chart.homeGeoPoint = { "longitude": 71.4704, "latitude": 51.1605};
            self.chart.homeZoomLevel = 7;


            // Disabling zoom and dragging
            self.chart.seriesContainer.draggable = false;
            self.chart.seriesContainer.resizable = false;
            self.chart.seriesContainer.events.disableType("doublehit");
            self.chart.chartContainer.background.events.disableType("doublehit");
            self.chart.chartContainer.wheelable = false;

            // Texts
            self.labelsContainer = self.chart.createChild(am4core.Container);
            self.labelsContainer.isMeasured = false;
            self.labelsContainer.x = 80;
            self.labelsContainer.y = 27;
            self.labelsContainer.layout = "horizontal";
            self.labelsContainer.zIndex = 10;


            self.title = self.labelsContainer.createChild(am4core.Label);
            self.title.text = "Train routes";
            self.title.fill = am4core.color("#cc0000");
            self.title.fontSize = 20;
            self.title.valign = "middle";
            self.title.dy = 2;
            self.title.marginLeft = 15;

            self.changeLink = self.chart.createChild(am4core.TextLink);
            self.changeLink.text = "Click to change origin city";
            self.changeLink.isMeasured = false;

            self.changeLink.events.on("hit", function() {
                if (self.currentOrigin == self.originImageSeries.dataItems.getIndex(0)) {
                    self.showLines(self.originImageSeries.dataItems.getIndex(1));
                }
                else {
                    self.showLines(self.originImageSeries.dataItems.getIndex(0));
                }
            });

            self.changeLink.x = 100;
            self.changeLink.y = 72;
            self.changeLink.fontSize = 13;

            // The world
            self.worldPolygonSeries = self.chart.series.push(new am4maps.MapPolygonSeries());
            self.worldPolygonSeries.useGeodata = true;
            self.worldPolygonSeries.fillOpacity = 0.6;
            self.worldPolygonSeries.exclude = ["AQ"];

            // Origin series
            self.originImageSeries = self.chart.series.push(new am4maps.MapImageSeries());
            self.originImageTemplate = self.originImageSeries.mapImages.template;

            // destination series (small targets)
            self.destinationImageSeries = self.chart.series.push(new am4maps.MapImageSeries());
            self.destinationImageTemplate = self.destinationImageSeries.mapImages.template;

            self.renderDestinationCities();
            self.renderOriginCities();

            // Line series
            self.lineSeries = self.chart.series.push(new am4maps.MapLineSeries());
            self.lineSeries.mapLines.template.line.strokeOpacity = 0.5;

            self.graticuleSeries = self.chart.series.push(new am4maps.GraticuleSeries());
            self.graticuleSeries.mapLines.template.line.strokeOpacity = 0.05;
        });

    }
}
