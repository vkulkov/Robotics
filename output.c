int x = 5;
int y = 3;
int z;
void setup()
{
	if (x > y)
	{
		z = x - y;
		x = y;
	}
	else
	{
		z = x;
	}
	pinMode(x, OUTPUT);
}
void loop()
{
	while(z > 0)
	{
		digitalWrite(x, HIGH);
		delay(1000);
		digitalWrite(x, LOW);
		delay(1000);
		z--;
	}
}
